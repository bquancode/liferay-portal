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

package com.liferay.portal.convert.database;

import com.liferay.portal.convert.BaseConvertProcess;

import javax.sql.DataSource;

/**
 * @author Alexander Chow
 * @deprecated As of 7.0.0, with no direct replacement
 */
@Deprecated
public class DatabaseConvertProcess extends BaseConvertProcess {

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public void destroy() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public String getDescription() {
		return null;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public String getParameterDescription() {
		return null;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public String[] getParameterNames() {
		return null;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEnabled() {
		return false;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	protected DataSource buildDatasource() throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	protected void doConvert() throws Exception {
		throw new UnsupportedOperationException();
	}

}