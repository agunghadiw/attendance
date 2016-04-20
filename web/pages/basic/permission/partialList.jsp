<%@page import="com.opensymphony.xwork2.ActionContext"%>
<% response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");%>
<%@ taglib prefix="s" uri="/struts-tags"%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="images/favicon.png">

    <title><s:text name="label.index"/></title>

    <!--Core CSS -->
    <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<s:url value="/css/bootstrap-reset.css"/>" rel="stylesheet">
    <link href="<s:url value="/font-awesome/css/font-awesome.css"/>" rel="stylesheet" />
    
    <!-- Custom styles for this template -->
    <link href="<s:url value="/css/style.css"/>" rel="stylesheet">
    <link href="<s:url value="/css/style-responsive.css"/>" rel="stylesheet" />

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]>
    <script src="<s:url value="/js/ie8-responsive-file-warning.js"/>"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<section id="container" >
<!--header start-->
<s:include value="/common/header.jsp"/>
<!--header end-->

<s:include value="/common/left_side.jsp"/>
<!--sidebar end-->
    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
        <!-- page start-->
			<div class="row">
    			<div class="col-md-12">
                <section class="panel">
                    <header class="panel-heading">
                        <s:property value="#session['permission_name']"/>
                        <span class="tools pull-right">
                            <a href="javascript:;" class="fa fa-chevron-down"></a>
                            <a href="javascript:;" class="fa fa-cog"></a>
                            <a href="javascript:;" class="fa fa-times"></a>
                         </span>
                    </header>
                    <div class="panel-body">
                    
                    <s:form cssClass="form-horizontal" role="form" id="hrForm">
			<s:hidden name="start"/>
			<s:hidden name="count"/>
			<s:hidden name="orderBy"/>
			<s:hidden name="ascDesc"/>
			<s:hidden name="previousPage"/>
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:token></s:token>
			<div class="form-group">
			    <label class="col-sm-2 control-label"><s:text name="label.permissionName"/></label>
			    <div class="col-sm-5">
			      <s:textfield cssClass="form-control" name="permissionNameX" placeHolder="Permission Name" />
			    </div>
			</div>
		  	<div class="form-group">
			    <label class="col-sm-2 control-label"><s:text name="label.link"/></label>
			    <div class="col-sm-5">
			      <s:textfield cssClass="form-control" name="linkX" placeHolder="Link" />
			    </div>
			    <div class="col-sm-5">
			    	<s:submit value="%{getText('label.search.link')}" cssClass="btn btn-sml"/>
			    </div>
			</div>
		  	<div class="row">
	      		<div class="col-md-5 text-left"><s:property value="%{pager}" escape="false"/></div>
	      		<div class="col-md-2">
		      		<div class="input-group input-group-sm">
		      			<s:textfield cssClass="form-control" name="gotoPage"  onchange="gotoPager();" placeHolder="Goto Page"/>
		      		</div>
		      	</div>
	      		<div class="col-md-5 text-right"><s:property value="%{pagerItem}" escape="false"/></div>
      		</div>
      	<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<thead>
			        <tr>
			        	<th>&nbsp;</th>
			          <th><s:text name="label.parent"/>#1</th>
			          <th><s:text name="label.parent"/>#2</th>
			          <th><s:text name="label.permissionName"/></th>
			          <th><s:text name="label.link"/></th>
			          <th><s:text name="label.isShow"/></th>
			          <th><s:text name="label.isWorkflow"/></th>
			          <th><s:text name="label.isLog"/></th>
			          <th>&nbsp;</th>
			        </tr>
		        </thead>
        <%
int i = 0;
try {
i = ((Integer)ActionContext.getContext().getValueStack().findValue("start")).intValue();
}catch(Exception ex){
i = 0;
}
%>
		         <tbody>
		            <s:iterator value="permissionList" status="sequence">
		            <tr>
			            <td><%=++i%>.</td>
			            <td><s:if test="parent!=null"><s:property value="parent.permissionName"/></s:if></td>
			            <td><s:if test="parent!=null"><s:if test="parent.parent!=null"><s:property value="parent.parent.permissionName"/></s:if></s:if></td>
			            <td><s:property value="permissionName"/></td>
			            <td><s:property value="link"/></td>
			            <td>
			            	<s:if test="show==true"><span class="label label-success"><s:text name="label.yes"/></span></s:if>
										<s:else><span class="label label-info"><s:text name="label.no"/></span></s:else>
			            </td>
			            <td>
			            	<s:if test="workflow==true"><span class="label label-success"><s:text name="label.yes"/></span></s:if>
							<s:else><span class="label label-info"><s:text name="label.no"/></span></s:else>
			            </td>
			            <td>
			            	<s:if test="log==true"><span class="label label-success"><s:text name="label.yes"/></span></s:if>
							<s:else><span class="label label-info"><s:text name="label.no"/></span></s:else>
			            </td>
			            <td>
			            	<a href="<s:url value="PermissionAction_edit.action"/>?permissionId=<s:property value="permissionId"/>"><i class="fa fa-pencil" title="Edit"></i></a>
			            </td>
		            </tr>
		            </s:iterator>
		        </tbody>
  			</table>
  			</div>
	  		<div class="row">
		      	<div class="col-md-5 text-left"><s:property value="%{pager}" escape="false"/></div>
		      	<div class="col-md-2">
		      		<div class="input-group input-group-sm">
		      		<s:textfield cssClass="form-control" name="gotoPage2" onchange="gotoPager2();" placeHolder="Goto Page"/>
		      		</div>
		      	</div>
	      		<div class="col-md-5 text-right"><s:property value="%{pagerItem}" escape="false"/></div>
     		</div>
  			</s:form>
                    
                        
                    </div>
                </section>
        		</div>
        	</div>
        	
        	
        
        <!-- page end-->
        </section>
    </section>
    <!--main content end-->
<!--right sidebar start-->
<s:include value="/common/right_side.jsp"/>
<!--right sidebar end-->

</section>

<!-- Placed js at the end of the document so the pages load faster -->

<!--Core js-->
<script src="<s:url value="/js/jquery.js"/>"></script>
<script src="<s:url value="/js/bootstrap.min.js"/>"></script>
<script class="include" type="text/javascript" src="<s:url value="/js/jquery.dcjqaccordion.2.7.js"/>"></script>
<script src="<s:url value="/js/jquery.scrollTo.min.js"/>"></script>
<script src="<s:url value="/js/jquery.slimscroll.min.js"/>"></script>
<script src="<s:url value="/js/jquery.nicescroll.js"/>"></script>

<!--Easy Pie Chart-->
<script src="<s:url value="/js/easypiechart/jquery.easypiechart.js"/>"></script>
<!--Sparkline Chart-->
<script src="<s:url value="/js/sparkline/jquery.sparkline.js"/>"></script>
<!--jQuery Flot Chart-->
<script src="<s:url value="/js/flot-chart/jquery.flot.js"/>"></script>
<script src="<s:url value="/js/flot-chart/jquery.flot.tooltip.min.js"/>"></script>
<script src="<s:url value="/js/flot-chart/jquery.flot.resize.js"/>"></script>
<script src="<s:url value="/js/flot-chart/jquery.flot.pie.resize.js"/>"></script>


<!--common script init for all pages-->
<script src="<s:url value="/js/scripts.js"/>"></script>
<script src="<s:url value="/js/paging.js"/>"></script>

</body>
</html>
