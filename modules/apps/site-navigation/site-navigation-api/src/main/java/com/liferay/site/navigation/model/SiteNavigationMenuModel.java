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

package com.liferay.site.navigation.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the SiteNavigationMenu service. Represents a row in the &quot;SiteNavigationMenu&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.site.navigation.model.impl.SiteNavigationMenuModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.site.navigation.model.impl.SiteNavigationMenuImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SiteNavigationMenu
 * @see com.liferay.site.navigation.model.impl.SiteNavigationMenuImpl
 * @see com.liferay.site.navigation.model.impl.SiteNavigationMenuModelImpl
 * @generated
 */
@ProviderType
public interface SiteNavigationMenuModel extends BaseModel<SiteNavigationMenu>,
	ShardedModel, StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a site navigation menu model instance should use the {@link SiteNavigationMenu} interface instead.
	 */

	/**
	 * Returns the primary key of this site navigation menu.
	 *
	 * @return the primary key of this site navigation menu
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this site navigation menu.
	 *
	 * @param primaryKey the primary key of this site navigation menu
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this site navigation menu.
	 *
	 * @return the uuid of this site navigation menu
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this site navigation menu.
	 *
	 * @param uuid the uuid of this site navigation menu
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the site navigation menu ID of this site navigation menu.
	 *
	 * @return the site navigation menu ID of this site navigation menu
	 */
	public long getSiteNavigationMenuId();

	/**
	 * Sets the site navigation menu ID of this site navigation menu.
	 *
	 * @param siteNavigationMenuId the site navigation menu ID of this site navigation menu
	 */
	public void setSiteNavigationMenuId(long siteNavigationMenuId);

	/**
	 * Returns the group ID of this site navigation menu.
	 *
	 * @return the group ID of this site navigation menu
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this site navigation menu.
	 *
	 * @param groupId the group ID of this site navigation menu
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this site navigation menu.
	 *
	 * @return the company ID of this site navigation menu
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this site navigation menu.
	 *
	 * @param companyId the company ID of this site navigation menu
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this site navigation menu.
	 *
	 * @return the user ID of this site navigation menu
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this site navigation menu.
	 *
	 * @param userId the user ID of this site navigation menu
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this site navigation menu.
	 *
	 * @return the user uuid of this site navigation menu
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this site navigation menu.
	 *
	 * @param userUuid the user uuid of this site navigation menu
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this site navigation menu.
	 *
	 * @return the user name of this site navigation menu
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this site navigation menu.
	 *
	 * @param userName the user name of this site navigation menu
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this site navigation menu.
	 *
	 * @return the create date of this site navigation menu
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this site navigation menu.
	 *
	 * @param createDate the create date of this site navigation menu
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this site navigation menu.
	 *
	 * @return the modified date of this site navigation menu
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this site navigation menu.
	 *
	 * @param modifiedDate the modified date of this site navigation menu
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this site navigation menu.
	 *
	 * @return the name of this site navigation menu
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this site navigation menu.
	 *
	 * @param name the name of this site navigation menu
	 */
	public void setName(String name);

	/**
	 * Returns the type of this site navigation menu.
	 *
	 * @return the type of this site navigation menu
	 */
	public int getType();

	/**
	 * Sets the type of this site navigation menu.
	 *
	 * @param type the type of this site navigation menu
	 */
	public void setType(int type);

	/**
	 * Returns the auto of this site navigation menu.
	 *
	 * @return the auto of this site navigation menu
	 */
	public boolean getAuto();

	/**
	 * Returns <code>true</code> if this site navigation menu is auto.
	 *
	 * @return <code>true</code> if this site navigation menu is auto; <code>false</code> otherwise
	 */
	public boolean isAuto();

	/**
	 * Sets whether this site navigation menu is auto.
	 *
	 * @param auto the auto of this site navigation menu
	 */
	public void setAuto(boolean auto);

	/**
	 * Returns the last publish date of this site navigation menu.
	 *
	 * @return the last publish date of this site navigation menu
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this site navigation menu.
	 *
	 * @param lastPublishDate the last publish date of this site navigation menu
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

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
	public int compareTo(SiteNavigationMenu siteNavigationMenu);

	@Override
	public int hashCode();

	@Override
	public CacheModel<SiteNavigationMenu> toCacheModel();

	@Override
	public SiteNavigationMenu toEscapedModel();

	@Override
	public SiteNavigationMenu toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}