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

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.Tuple;

import java.io.IOException;

import java.lang.reflect.Field;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * @author Cristina González
 * @author Miguel Pastor
 * @deprecated As of 7.0.0, with no direct replacement
 */
@Deprecated
public class ModelMigratorImpl implements ModelMigrator {

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public void migrate(
			DataSource dataSource, List<Class<? extends BaseModel<?>>> models)
		throws IOException, SQLException {

		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	protected Map<String, Tuple> getModelTableDetails(Class<?> implClass) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	protected Tuple getTableDetails(
		Class<?> implClass, Field tableField, String tableFieldVar) {

		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	protected void migrateModel(
			Map<String, Tuple> modelTableDetails, DB db, Connection connection)
		throws IOException {

		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	protected void migrateTable(
		DB db, Connection connection, String tableName, Object[][] columns,
		String sqlCreate) {

		throw new UnsupportedOperationException();
	}

}