<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
Role role = (Role)request.getAttribute("edit_role_permissions.jsp-role");

String portletResource = (String)request.getAttribute("edit_role_permissions.jsp-portletResource");

String curPortletResource = (String)request.getAttribute("edit_role_permissions.jsp-curPortletResource");
String curModelResource = (String)request.getAttribute("edit_role_permissions.jsp-curModelResource");
String curModelResourceName = (String)request.getAttribute("edit_role_permissions.jsp-curModelResourceName");

List<String> selectedResourceBlockPermissionActionIds = (List<String>)request.getAttribute("edit_role_permissions_form.jsp-selectedResourceBlockPermissionActionIds");
List<String> unselectedResourceBlockPermissionActionIds = (List<String>)request.getAttribute("edit_role_permissions_form.jsp-unselectedResourceBlockPermissionActionIds");

Portlet curPortlet = null;
String curPortletId = StringPool.BLANK;

if (Validator.isNotNull(curPortletResource)) {
	curPortlet = PortletLocalServiceUtil.getPortletById(themeDisplay.getCompanyId(), curPortletResource);
	curPortletId = curPortlet.getPortletId();
}

List curActions = ResourceActionsUtil.getResourceActions(curPortletResource, curModelResource);

curActions = ListUtil.sort(curActions, new ActionComparator(locale));

List guestUnsupportedActions = ResourceActionsUtil.getResourceGuestUnsupportedActions(curPortletResource, curModelResource);

List<String> headerNames = new ArrayList<String>();

headerNames.add("action");

boolean showScope = _isShowScope(request, role, curModelResource, curPortletId);

if (showScope) {
	headerNames.add("sites");
}

SearchContainer searchContainer = new SearchContainer(liferayPortletRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, liferayPortletResponse.createRenderURL(), headerNames, "there-are-no-actions");

searchContainer.setRowChecker(new ResourceActionRowChecker(liferayPortletResponse));

int total = curActions.size();

searchContainer.setTotal(total);

List results = curActions;

searchContainer.setResults(results);

List resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	String actionId = (String)results.get(i);

	if (role.getName().equals(RoleConstants.GUEST) && guestUnsupportedActions.contains(actionId)) {
		continue;
	}

	PanelCategoryHelper panelCategoryHelper = (PanelCategoryHelper)request.getAttribute(ApplicationListWebKeys.PANEL_CATEGORY_HELPER);

	if (Validator.isNotNull(curPortletResource)) {
		if (actionId.equals(ActionKeys.ACCESS_IN_CONTROL_PANEL) && !panelCategoryHelper.hasPanelApp(curPortletId)) {
			continue;
		}

		if (actionId.equals(ActionKeys.ADD_TO_PAGE) && _hasHiddenPortletCategory(curPortlet)) {
			continue;
		}
	}

	String curResource = null;

	if (Validator.isNull(curModelResource)) {
		curResource = curPortletResource;
	}
	else {
		curResource = curModelResource;
	}

	String target = curResource + actionId;
	int scope = ResourceConstants.SCOPE_COMPANY;
	boolean supportsFilterByGroup = false;
	List<Group> groups = Collections.emptyList();

	String groupIds = ParamUtil.getString(request, "groupIds" + target, null);

	long[] groupIdsArray = StringUtil.split(groupIds, 0L);

	List<String> groupNames = new ArrayList<String>();

	if (ResourceBlockLocalServiceUtil.isSupported(curResource)) {
		if (ResourceTypePermissionLocalServiceUtil.hasEitherScopePermission(role.getCompanyId(), curModelResource, role.getRoleId(), actionId)) {
			selectedResourceBlockPermissionActionIds.add(target);
		}
		else {
			unselectedResourceBlockPermissionActionIds.add(target);
		}
	}

	if (role.getType() == RoleConstants.TYPE_REGULAR) {
		if (Validator.isNotNull(portletResource)) {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletResource);

			if ((portlet!= null) && panelCategoryHelper.containsPortlet(portlet.getPortletId(), PanelCategoryKeys.SITE_ADMINISTRATION)) {
				supportsFilterByGroup = true;
			}
		}

		if (!supportsFilterByGroup && !ResourceActionsUtil.isPortalModelResource(curResource) && !portletResource.equals(PortletKeys.PORTAL)) {
			supportsFilterByGroup = true;
		}

		LinkedHashMap<String, Object> groupParams = new LinkedHashMap<String, Object>();

		RolePermissions rolePermissions = new RolePermissions(curResource, ResourceConstants.SCOPE_GROUP, actionId, role.getRoleId());

		groupParams.put("rolePermissions", rolePermissions);

		groups = GroupLocalServiceUtil.search(company.getCompanyId(), new long[] {PortalUtil.getClassNameId(Company.class), PortalUtil.getClassNameId(Group.class), PortalUtil.getClassNameId(Organization.class), PortalUtil.getClassNameId(UserPersonalSite.class)}, null, null, groupParams, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		groupIdsArray = new long[groups.size()];

		for (int j = 0; j < groups.size(); j++) {
			Group group = (Group)groups.get(j);

			groupIdsArray[j] = group.getGroupId();

			groupNames.add(HtmlUtil.escape(group.getDescriptiveName(locale)));
		}

		if (!groups.isEmpty()) {
			scope = ResourceConstants.SCOPE_GROUP;
		}
	}
	else {
		scope = ResourceConstants.SCOPE_GROUP_TEMPLATE;
	}

	ResultRow row = new ResultRow(new Object[] {role, actionId, curResource, target, scope, supportsFilterByGroup, groups, groupIdsArray, groupNames}, target, i);

	row.addText(_getActionLabel(request, themeDisplay, curResource, actionId));

	if (showScope) {
		row.addJSP("/edit_role_permissions_resource_scope.jsp", application, request, response);
	}

	resultRows.add(row);
}
%>

<liferay-ui:search-iterator
	paginate="<%= false %>"
	searchContainer="<%= searchContainer %>"
/>

<%!
private boolean _hasHiddenPortletCategory(Portlet portlet) {
	PortletCategory portletCategory = (PortletCategory)WebAppPool.get(portlet.getCompanyId(), WebKeys.PORTLET_CATEGORY);

	PortletCategory hiddenPortletCategory = portletCategory.getCategory("category.hidden");

	Set<String> portletIds = hiddenPortletCategory.getPortletIds();

	if (portletIds.contains(portlet.getPortletId())) {
		return true;
	}

	return false;
}
%>