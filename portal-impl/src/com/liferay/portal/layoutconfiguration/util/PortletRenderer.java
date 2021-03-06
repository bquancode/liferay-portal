/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.layoutconfiguration.util;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.PortletContainerException;
import com.liferay.portal.kernel.portlet.PortletContainerUtil;
import com.liferay.portal.kernel.portlet.RestrictPortletServletRequest;
import com.liferay.portal.kernel.servlet.BufferCacheServletResponse;
import com.liferay.portal.kernel.util.Mergeable;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.ThreadLocalBinder;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shuyang Zhou
 * @author Neil Griffin
 */
public class PortletRenderer {

	public PortletRenderer(
		Portlet portlet, String columnId, Integer columnCount,
		Integer columnPos) {

		_portlet = portlet;
		_columnId = columnId;
		_columnCount = columnCount;
		_columnPos = columnPos;
	}

	public void finishParallelRender() {
		if (_restrictPortletServletRequest != null) {
			_restrictPortletServletRequest.mergeSharedAttributes();
		}
	}

	public Callable<StringBundler> getCallable(
		HttpServletRequest request, HttpServletResponse response,
		Map<String, Object> headerRequestAttributes) {

		return new PortletRendererCallable(
			request, response, headerRequestAttributes);
	}

	public Portlet getPortlet() {
		return _portlet;
	}

	public StringBundler render(
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> headerRequestAttributes)
		throws PortletContainerException {

		request = PortletContainerUtil.setupOptionalRenderParameters(
			request, null, _columnId, _columnPos, _columnCount);

		_copyHeaderRequestAttributes(headerRequestAttributes, request);

		return _render(request, response);
	}

	public StringBundler renderAjax(
			HttpServletRequest request, HttpServletResponse response)
		throws PortletContainerException {

		request = PortletContainerUtil.setupOptionalRenderParameters(
			request, _RENDER_PATH, _columnId, _columnPos, _columnCount);

		_restrictPortletServletRequest = (RestrictPortletServletRequest)request;

		return _render(request, response);
	}

	public StringBundler renderError(
			HttpServletRequest request, HttpServletResponse response)
		throws PortletContainerException {

		request = PortletContainerUtil.setupOptionalRenderParameters(
			request, null, _columnId, _columnPos, _columnCount);

		request.setAttribute(
			WebKeys.PARALLEL_RENDERING_TIMEOUT_ERROR, Boolean.TRUE);

		_restrictPortletServletRequest = (RestrictPortletServletRequest)request;

		try {
			return _render(request, response);
		}
		finally {
			request.removeAttribute(WebKeys.PARALLEL_RENDERING_TIMEOUT_ERROR);
		}
	}

	public Map<String, Object> renderHeaders(
			HttpServletRequest request, HttpServletResponse response,
			List<String> attributePrefixes)
		throws PortletContainerException {

		request = PortletContainerUtil.setupOptionalRenderParameters(
			request, null, _columnId, _columnPos, _columnCount);

		BufferCacheServletResponse bufferCacheServletResponse =
			new BufferCacheServletResponse(response);

		PortletContainerUtil.renderHeaders(
			request, bufferCacheServletResponse, _portlet);

		Map<String, Object> headerRequestAttributes = new HashMap<>();

		Enumeration<String> attributeNames = request.getAttributeNames();

		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();

			if (attributeName.startsWith("javax.portlet.faces")) {
				headerRequestAttributes.put(
					attributeName, request.getAttribute(attributeName));
			}
			else if (attributePrefixes != null) {
				for (String attributePrefix : attributePrefixes) {
					if (attributeName.startsWith(attributePrefix)) {
						headerRequestAttributes.put(
							attributeName, request.getAttribute(attributeName));

						break;
					}
				}
			}
		}

		return headerRequestAttributes;
	}

	private void _copyHeaderRequestAttributes(
		Map<String, Object> headerRequestAttributes,
		HttpServletRequest request) {

		if (headerRequestAttributes != null) {
			for (Map.Entry<String, Object> entry :
					headerRequestAttributes.entrySet()) {

				request.setAttribute(entry.getKey(), entry.getValue());
			}
		}
	}

	private StringBundler _render(
			HttpServletRequest request, HttpServletResponse response)
		throws PortletContainerException {

		BufferCacheServletResponse bufferCacheServletResponse =
			new BufferCacheServletResponse(response);

		Object lock = request.getAttribute(
			WebKeys.PARALLEL_RENDERING_MERGE_LOCK);

		request.setAttribute(WebKeys.PARALLEL_RENDERING_MERGE_LOCK, null);

		Object portletParallelRender = request.getAttribute(
			WebKeys.PORTLET_PARALLEL_RENDER);

		request.setAttribute(WebKeys.PORTLET_PARALLEL_RENDER, Boolean.FALSE);

		try {
			PortletContainerUtil.render(
				request, bufferCacheServletResponse, _portlet);

			return bufferCacheServletResponse.getStringBundler();
		}
		catch (IOException ioe) {
			throw new PortletContainerException(ioe);
		}
		finally {
			request.setAttribute(WebKeys.PARALLEL_RENDERING_MERGE_LOCK, lock);
			request.setAttribute(
				WebKeys.PORTLET_PARALLEL_RENDER, portletParallelRender);
		}
	}

	private static final String _RENDER_PATH =
		"/html/portal/load_render_portlet.jsp";

	private final Integer _columnCount;
	private final String _columnId;
	private final Integer _columnPos;
	private final Portlet _portlet;
	private RestrictPortletServletRequest _restrictPortletServletRequest;

	private abstract class CopyThreadLocalCallable<T> implements Callable<T> {

		public CopyThreadLocalCallable(boolean readOnly, boolean clearOnExit) {
			this(null, readOnly, clearOnExit);
		}

		public CopyThreadLocalCallable(
			ThreadLocalBinder threadLocalBinder, boolean readOnly,
			boolean clearOnExit) {

			_threadLocalBinder = threadLocalBinder;

			if (_threadLocalBinder != null) {
				_threadLocalBinder.record();
			}

			if (readOnly) {
				_longLivedThreadLocals = Collections.unmodifiableMap(
					CentralizedThreadLocal.getLongLivedThreadLocals());
				_shortLivedlThreadLocals = Collections.unmodifiableMap(
					CentralizedThreadLocal.getShortLivedThreadLocals());
			}
			else {
				_longLivedThreadLocals =
					CentralizedThreadLocal.getLongLivedThreadLocals();
				_shortLivedlThreadLocals =
					CentralizedThreadLocal.getShortLivedThreadLocals();
			}

			_clearOnExit = clearOnExit;
		}

		@Override
		public final T call() throws Exception {
			CentralizedThreadLocal.setThreadLocals(
				_longLivedThreadLocals, _shortLivedlThreadLocals);

			if (_threadLocalBinder != null) {
				_threadLocalBinder.bind();
			}

			try {
				return doCall();
			}
			finally {
				if (_clearOnExit) {
					if (_threadLocalBinder != null) {
						_threadLocalBinder.cleanUp();
					}

					CentralizedThreadLocal.clearLongLivedThreadLocals();
					CentralizedThreadLocal.clearShortLivedThreadLocals();
				}
			}
		}

		public abstract T doCall() throws Exception;

		private final boolean _clearOnExit;
		private final Map<CentralizedThreadLocal<?>, Object>
			_longLivedThreadLocals;
		private final Map<CentralizedThreadLocal<?>, Object>
			_shortLivedlThreadLocals;
		private final ThreadLocalBinder _threadLocalBinder;

	}

	private class PortletRendererCallable
		extends CopyThreadLocalCallable<StringBundler> {

		public PortletRendererCallable(
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> headerRequestAttributes) {

			super(
				ParallelRenderThreadLocalBinderUtil.getThreadLocalBinder(),
				false, true);

			_request = request;
			_response = response;
			_headerRequestAttributes = headerRequestAttributes;
		}

		@Override
		public StringBundler doCall() throws Exception {
			HttpServletRequest request =
				PortletContainerUtil.setupOptionalRenderParameters(
					_request, null, _columnId, _columnPos, _columnCount);

			_copyHeaderRequestAttributes(_headerRequestAttributes, request);

			_restrictPortletServletRequest =
				(RestrictPortletServletRequest)request;

			try {
				_split(_request, _restrictPortletServletRequest);

				return _render(request, _response);
			}
			catch (Exception e) {

				// Under parallel rendering context. An interrupted state means
				// the call was cancelled and so we should not rethrow the
				// exception.

				Thread currentThread = Thread.currentThread();

				if (!currentThread.isInterrupted()) {
					throw e;
				}

				return null;
			}
		}

		private void _split(
			HttpServletRequest request,
			RestrictPortletServletRequest restrictPortletServletRequest) {

			Enumeration<String> attributeNames = request.getAttributeNames();

			while (attributeNames.hasMoreElements()) {
				String attributeName = attributeNames.nextElement();

				Object attribute = request.getAttribute(attributeName);

				if (!(attribute instanceof Mergeable<?>) ||
					!RestrictPortletServletRequest.isSharedRequestAttribute(
						attributeName)) {

					continue;
				}

				Mergeable<?> mergeable = (Mergeable<?>)attribute;

				restrictPortletServletRequest.setAttribute(
					attributeName, mergeable.split());
			}
		}

		private final Map<String, Object> _headerRequestAttributes;
		private final HttpServletRequest _request;
		private final HttpServletResponse _response;

	}

}