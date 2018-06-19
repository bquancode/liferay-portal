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

package com.liferay.portal.tools.service.builder.test.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.version.VersionedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the VersionedEntry service. Represents a row in the &quot;VersionedEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.tools.service.builder.test.model.impl.VersionedEntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.tools.service.builder.test.model.impl.VersionedEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VersionedEntry
 * @see com.liferay.portal.tools.service.builder.test.model.impl.VersionedEntryImpl
 * @see com.liferay.portal.tools.service.builder.test.model.impl.VersionedEntryModelImpl
 * @generated
 */
@ProviderType
public interface VersionedEntryModel extends BaseModel<VersionedEntry>, MVCCModel,
	VersionedModel<VersionedEntryVersion> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a versioned entry model instance should use the {@link VersionedEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this versioned entry.
	 *
	 * @return the primary key of this versioned entry
	 */
	@Override
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this versioned entry.
	 *
	 * @param primaryKey the primary key of this versioned entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this versioned entry.
	 *
	 * @return the mvcc version of this versioned entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this versioned entry.
	 *
	 * @param mvccVersion the mvcc version of this versioned entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the head ID of this versioned entry.
	 *
	 * @return the head ID of this versioned entry
	 */
	@Override
	public long getHeadId();

	/**
	 * Sets the head ID of this versioned entry.
	 *
	 * @param headId the head ID of this versioned entry
	 */
	@Override
	public void setHeadId(long headId);

	/**
	 * Returns the versioned entry ID of this versioned entry.
	 *
	 * @return the versioned entry ID of this versioned entry
	 */
	public long getVersionedEntryId();

	/**
	 * Sets the versioned entry ID of this versioned entry.
	 *
	 * @param versionedEntryId the versioned entry ID of this versioned entry
	 */
	public void setVersionedEntryId(long versionedEntryId);

	/**
	 * Returns the group ID of this versioned entry.
	 *
	 * @return the group ID of this versioned entry
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this versioned entry.
	 *
	 * @param groupId the group ID of this versioned entry
	 */
	public void setGroupId(long groupId);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(VersionedEntry versionedEntry);

	@Override
	public int hashCode();

	@Override
	public CacheModel<VersionedEntry> toCacheModel();

	@Override
	public VersionedEntry toEscapedModel();

	@Override
	public VersionedEntry toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}