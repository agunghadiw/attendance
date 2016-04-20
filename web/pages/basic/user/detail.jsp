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
    
    <link href="<s:url value="/css/skins/minimal/minimal.css"/>" rel="stylesheet" />
    
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
			
			<s:form cssClass="form-horizontal" id="hrForm">
			<s:hidden name="userId"/>
			<s:if test="(userId==0)">
			<s:hidden name="isActive"/>
			</s:if>
			<s:if test="userId>0">
			<s:hidden name="userPass"/>
			</s:if>
			<s:hidden name="previousPage" />
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:hidden name="createBy"/>
			<s:hidden name="changeBy"/>
			<s:token></s:token>
			<div class="col-xs-12">
				<h4><s:property value="#session['permission_name']"/></h4>
				<hr>
			</div>
			<s:if test="(hasActionErrors() || hasFieldErrors() || hasActionMessages() )">
   		<div class="col-xs-12 alert alert-danger alert-dismissable">
   			<i class="fa fa-ban"></i>
        <s:actionerror/><s:fielderror></s:fielderror><s:actionmessage/>
      </div>
      </s:if>
			<div class="form-group">
				<label  class="col-sm-3 control-label"><s:text name="label.organization"/></label>
				<div class="col-sm-4">
		  			<s:select cssClass="form-control" list="organizationList" name="organizationId" listKey="organizationId" listValue="name" headerKey="-1" headerValue="Select bellow" disabled="%{readOnly}"/>
		  		</div>
		  	</div>
		  	<div class="form-group">
				<label  class="col-sm-3 control-label"><s:text name="label.branch"/></label>
				<div class="col-sm-4">
		  			<s:select cssClass="form-control" list="branchList" name="branchId" listKey="branchId" listValue="name" headerKey="" headerValue="Select bellow" disabled="%{readOnly}"/>
		  		</div>	
		  	</div>
		  	<div class="form-group">
				<label  class="col-sm-3 control-label"><s:text name="label.role"/></label>
				<div class="col-sm-4">
		    		<s:select cssClass="form-control" list="roleList" name="roleIdList" listKey="roleId" listValue="roleName" disabled="%{readOnly}" multiple="true" size="10"/>
				</div>
			</div>
			<s:if test="(userId==0)">
			<div class="form-group">
				<label  class="col-sm-3 control-label"><s:text name="label.userName"/></label>
				<div class="col-sm-5">
					<s:textfield cssClass="form-control" name="userName" readonly="%{readOnly}"/>
				</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-3 control-label"><s:text name="label.userPass"/></label>
				<div class="col-sm-4">
					<s:password cssClass="form-control" name="userPass" readonly="%{readOnly}"/>
				</div>
			</div>
			</s:if>
			<s:else>
			<div class="form-group">
				<label  class="col-sm-3 control-label"><s:text name="label.userName"/></label>
				<div class="col-sm-5">
					<s:textfield cssClass="form-control" name="userName" readonly="true"/>
				</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-3 control-label"><s:text name="label.userPass"/></label>
				<div class="col-sm-4">
					<s:password cssClass="form-control" name="confirmUserPass" readonly="%{readOnly}"/>
				</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-3 control-label"><s:text name="label.isActive"/></label>
				<div class="col-sm-4 icheck minimal">
					<div class="checkbox ">
						<s:checkbox name="active" disabled="%{readOnly}" />&nbsp;<label><s:text name="label.yes"/></label>
					</div>
				</div>
		  </div>
			</s:else>		
		  	<div class="form-group">
				<div class="col-sm-offset-3 col-sm-5">
					<input type="button" class="btn btn-sml" value="<s:text name="label.edit.link"/>" onclick="window.location.href=('<s:url value="/basic/UserAction_edit.action"/>?userId=<s:property value="userId"/>');"/>&nbsp;
					<input type="button" class="btn btn-sml" value="<s:text name="label.delete.link"/>" onclick="window.location.href=('<s:url value="/basic/UserAction_delete.action"/>?userId=<s:property value="userId"/>');"/>&nbsp;
					<input type="button" class="btn btn-sml" value="<s:text name="label.back.link"/>" onclick="window.location.href=('<s:url value="/basic/UserAction_partialList.action"/>');"/>
				</div>
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
<script src="<s:url value="/js/icheck.min.js"/>"></script>

<script>
$(document).ready(function(){
	$('.minimal input').iCheck({
        checkboxClass: 'icheckbox_minimal',
        radioClass: 'iradio_minimal',
        increaseArea: '20%' // optional
    });
});
</script>

<!--common script init for all pages-->
<script src="<s:url value="/js/scripts.js"/>"></script>
<script src="<s:url value="/js/paging.js"/>"></script>

</body>
</html>



