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
			
			
			
			<s:form cssClass="form-horizontal" id="hrForm">
			<s:hidden name="organizationId"/>
			<s:hidden name="previousPage" />
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:hidden name="createBy"/>
			<s:hidden name="changeBy"/>
			<s:hidden name="subaction"/>
			<s:token></s:token>
			<s:if test="(hasActionErrors() || hasFieldErrors() || hasActionMessages() )">
		   		<div class="col-xs-12 alert alert-danger alert-dismissable">
		   			<i class="fa fa-ban"></i>
		        	<s:actionerror/><s:fielderror></s:fielderror><s:actionmessage/>
		      	</div>
		    </s:if>
		    
		    
		    <div class="submaintab">
					
					<ul class="nav nav-tabs">
						<li class="active"><a href="#a" data-toggle="tab">Organization</a></li>
					 	<li><a href="#b" data-toggle="tab">Organization Setup</a></li>
					 	<li><a href="#c" data-toggle="tab">Auto Number</a></li>
					</ul>
					
					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane fade in active" id="a">
							<br>
							<div class="form-group">
								<label class="col-sm-3 control-label"><s:text name="label.code"/></label>
								<div class="col-sm-3">
									<s:textfield cssClass="form-control" name="code" readonly="%{readOnly}" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><s:text name="label.name"/></label>
								<div class="col-sm-5">
									<s:textfield cssClass="form-control" name="name" readonly="%{readOnly}" />
								</div>
							</div>
							
						</div>
						
						<div class="tab-pane fade " id="b">
							<br>
							<div class="form-group">
								<label class="col-sm-3 control-label"><s:text name="label.sodDate"/></label>
								<div class="col-sm-3">
									<div class="input-group">
										<s:textfield cssClass="datePicker form-control" id="sodDate" name="organizationSetup.sodDate" readonly="%{readOnly}" placeholder="dd mmm yyyy"/><label for="date-picker-2" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
									</div>
								</div>
							</div>
						</div>
						
						<div class="tab-pane fade" id="c">
							<br>
						</div>
						
					</div>
			
				</div>
		    

				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-5">
						<input type="button" class="btn btn-sml" value="<s:text name="label.edit.link"/>" onclick="window.location.href=('<s:url value="OrganizationAction_edit.action"/>?organizationId=<s:property value="organizationId"/>');"/>&nbsp;
						<input type="button" class="btn btn-sml" value="<s:text name="label.delete.link"/>" onclick="window.location.href=('<s:url value="OrganizationAction_delete.action"/>?organizationId=<s:property value="organizationId"/>');"/>&nbsp;
						<input type="button" class="btn btn-sml" value="<s:text name="label.back.link"/>" onclick="window.location.href=('<s:url value="OrganizationAction_partialList.action"/>');"/>
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


<!--common script init for all pages-->
<script src="<s:url value="/js/scripts.js"/>"></script>
<script src="<s:url value="/js/paging.js"/>"></script>

</body>
</html>


