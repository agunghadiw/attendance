<%@ taglib prefix="s" uri="/struts-tags"%>

<aside>
    <div id="sidebar" class="nav-collapse">
        <!-- sidebar menu start-->            
        <div class="leftside-navigation">
        	<s:if test="#session['permissions']!=null">
            <ul class="sidebar-menu" id="nav-accordion">
            <s:iterator value="#session['permissions']">
            <s:if test="show"> 
            <s:if test="(permissionChilds.size()==0)">
            <s:if test="(#session['permission_name'].equalsIgnoreCase(permissionName))"><li class="active"></s:if><s:else><li></s:else>
                <a href="<%=request.getContextPath() %><s:property value="link" />">
                    <i class="<s:property value="iconName" />"></i>
                    <span><s:property value="permissionName" /></span>
                </a>
            </li>
            </s:if>
            <s:else>
            <li class="sub-menu">
                <a href="javascript:;" <s:if test="(#session['parent_permission_name'].equalsIgnoreCase(permissionName))">class="active"</s:if>>
                    <i class="<s:property value="iconName" />"></i>
                    <span><s:property value="permissionName" /></span>
                </a>
                <ul class="sub">
                	<s:iterator value="permissionChilds">
		            <s:if test="show">
                    <s:if test="(#session['permission_name'].equalsIgnoreCase(permissionName))"><li class="active"></s:if><s:else><li></s:else><a href="<%=request.getContextPath() %><s:property value="link" />"><s:property value="permissionName" /></a></li>
                    </s:if>
                    </s:iterator>
                </ul>                
            </li>
            </s:else>
            </s:if>
            </s:iterator>
        </ul>
        </s:if>
        
        </div>        
<!-- sidebar menu end-->
    </div>
</aside>