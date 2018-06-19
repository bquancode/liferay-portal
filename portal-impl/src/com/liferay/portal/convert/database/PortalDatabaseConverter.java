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

import com.liferay.portal.convert.util.ModelMigrator;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.List;

import javax.sql.DataSource;

/**
 * @author Cristina González
 * @deprecated As of 7.0.0, with no direct replacement
 */
@Deprecated
public class PortalDatabaseConverter implements DatabaseConverter {

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public void convert(DataSource dataSource) throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public void setModelMigrator(ModelMigrator modelMigrator) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	protected List<Class<? extends BaseModel<?>>> getModelClassNames(
		String regex) {

		throw new UnsupportedOperationException();
	}

}