
<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone")>

<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
	<#if has_navigation && is_setup_complete>
		<nav class="${nav_css_class} site-navigation" id="navigation" role="navigation">
			<div class="navbar-form navbar-right" role="search">
				<@liferay.search default_preferences="${freeMarkerPortletPreferences}" />
			</div>

			<div class="navbar-right">
		        <@liferay.navigation_menu default_preferences="${freeMarkerPortletPreferences}" />
                <div id="navbar_com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet">
                    <ul class="nav navbar-nav nav-tabs">    
                            <#assign  count = 0 />
                            <#list nav_items as nav_item>
                                <#assign  count += 1  />
                                <#assign  nav_item_class = "lfr-nav-item -" + count />
                        
                                <#if count == 1>
                                    <#assign  nav_item_class = nav_item_class + " first" />
                                </#if>

                                <#if nav_item.isSelected() >
                                    <#assign nav_item_class = nav_item_class + " selected active" />
                                </#if>
                                
                                <li class="${nav_item_class}">
                                    <#if nav_item.hasChildren()>
                                        <style>
                                            .lfr-nav-item:hover > ul.child-menu,
                                            ul.child-menu:hover .grandchild-menu {
                                                border-bottom: 2px dotted white;
                                                display: block;
                                                max-height: 500px;
                                                max-width: 250px;
                                                overflow: visible;
                                                text-align: center;
                                                right: auto;
                                            }

                                            li:hover ul.child-menu .grandchild-menu {
                                                left: 5%;
                                                margin-top: 3.5%;
                                            }
                                        </style>
                                    <#else>
                                    </#if>

                                    <a href="${nav_item.getURL()}" ${nav_item.getTarget()}>
                                        <span>${nav_item.getName()}</span>
                                        <#if nav_item.hasChildren()>
                                            <span class="lfr-nav-child-toggle"><i class="icon-caret-down"></i></span>
                                        </#if>
                                    </a>

                                    <#if nav_item.hasChildren()>
                                        <ul class="dropdown-menu child-menu" role="menu">
                                            <#list nav_item.getChildren() as nav_child>
                                                <#if nav_child.isSelected()>
                                                    <li class="active selected nav">
                                                <#else>
                                                    <li class="nav">
                                                </#if>
                                                        <a href="${nav_child.getURL()}" ${nav_child.getTarget()}>${nav_child.getName()}
                                                            <span>${nav_item.getName()}</span>
                                                            <#if nav_child.hasChildren()>
                                                                <span class="lfr-nav-child-toggle"><i class="icon-caret-down"></i></span>
                                                            </#if>
                                                        </a>

                                                        <#if nav_child.hasChildren()>
                                                            <ul class="dropdown-menu child-menu grandchild-menu" role="menu">
                                                                <#list nav_child.getChildren() as nav_grandchild>
                                                                    <#if nav_grandchild.isSelected()>
                                                                        <li class="selected nav active grandsub">
                                                                    <#else>
                                                                        <li class="grandsub nav">
                                                                    </#if>
                                                                            <a href="${nav_grandchild.getURL()}" ${nav_grandchild.getTarget()}>${nav_grandchild.getName()}</a>
                                                                        </li>
                                                                </#list>
                                                            </ul>
                                                        </#if>

                                                    </li>
                                            </#list>
                                        </ul>
                                    </#if>
                                </li>
                                
                            </#list>
                    </ul>
                </div>

            </div>
		</nav>
	</#if>
</div>

<#assign VOID = freeMarkerPortletPreferences.reset()>