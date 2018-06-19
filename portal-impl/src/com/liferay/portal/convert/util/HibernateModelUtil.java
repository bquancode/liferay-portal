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

package com.liferay.portal.convert.util;

import com.liferay.portal.kernel.model.BaseModel;

import java.util.List;

/**
 * @author Cristina González
 * @deprecated As of 7.0.0, with no direct replacement
 */
@Deprecated
public class HibernateModelUtil {

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public static List<Class<? extends BaseModel<?>>> getModelClassNames(
		ClassLoader classLoader, String regex) {

		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	protected static Class<? extends BaseModel<?>> getImplClass(
		ClassLoader classLoader, String implClassName) {

		throw new UnsupportedOperationException();
	}

}