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
    <link href="<s:url value="/css/jquery-ui-1.10.1.custom.min.css"/>" rel="stylesheet" type="text/css" />
    
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
			<s:hidden name="applicationSetupId"/>
			<s:hidden name="previousPage" />
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:hidden name="createBy"/>
			<s:hidden name="changeBy"/>
			<s:token></s:token>
			<s:if test="(hasActionErrors() || hasFieldErrors() || hasActionMessages() )">
		   		<div class="col-xs-12 alert alert-danger alert-dismissable">
		   			<i class="fa fa-ban"></i>
		        	<s:actionerror/><s:fielderror></s:fielderror><s:actionmessage/>
		      	</div>
		    </s:if>

    	<div class="form-group">
			<label class="col-sm-3 control-label"><s:text name="label.isEmailInternalEnable"/></label>
			<div class="col-sm-5">
				<s:checkbox name="emailInternalEnable" disabled="%{readOnly}" />&nbsp;<s:text name="label.yes"/>
			</div>
  		</div>
  		
		  <div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.smtpEmail"/></label>
				<div class="col-sm-5">
					<s:textarea cssClass="form-control" name="smtpEmail" readonly="%{readOnly}" />
					<span class="help-block">SMTP Email address for sending email.</span>
				</div>
			</div>
			<div class="form-group">
		  		<label class="col-sm-3 control-label"><s:text name="label.smtpUserNamePassword"/></label>
				<div class="col-sm-3">
					<s:textfield cssClass="form-control" name="smtpUserName" readonly="%{readOnly}" placeHolder="Username" />
				</div>
				<div class="col-sm-3">
					<s:textfield cssClass="form-control" name="smtpPassword" readonly="%{readOnly}" placeHolder="Password" />
				</div>
		  </div>
		  <div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.fromEmailAddress"/></label>
				<div class="col-sm-5">
					<s:textfield cssClass="form-control" name="fromEmailAddress" readonly="%{readOnly}" />
				</div>
			</div>
		  	
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.userPassHistory"/></label>
				<div class="col-sm-1">
					<s:textfield cssClass="form-control" name="userPassHistory" readonly="%{readOnly}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.defaultUserPassDuration"/></label>
				<div class="col-sm-1">
					<s:textfield cssClass="form-control" name="defaultUserPassDuration" readonly="%{readOnly}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.isAlphabetUserPass"/></label>
				<div class="col-sm-5">
					<s:checkbox name="alphabetUserPass" disabled="%{readOnly}" />&nbsp;<s:text name="label.yes"/>
				</div>
		  	</div>
		  	<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.isNumericUserPass"/></label>
				<div class="col-sm-5">
					<s:checkbox name="numericUserPass" disabled="%{readOnly}" />&nbsp;<s:text name="label.yes"/>
		  		</div>
		  	</div>
		  	<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.isUpperCaseLetter"/></label>
		  		<div class="col-sm-5">
		  			<s:checkbox name="upperCaseLetter" disabled="%{readOnly}" />&nbsp;<s:text name="label.yes"/>
		  		</div>
		  	</div>
		  	<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.minUserPassLength"/></label>
				<div class="col-sm-1">
					<s:textfield cssClass="form-control" name="minUserPassLength" readonly="%{readOnly}" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.userRole"/></label>
				<div class="col-sm-5">
					<s:select cssClass="form-control" id="defaultUserRoleId" name="defaultUserRoleId" list="userRoles" listKey="roleId" listValue="roleName" headerKey="" headerValue="Select below" disabled="%{readOnly}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.employeeNumber"/></label>
				<div class="col-sm-5">
					<s:textarea cssClass="form-control" id="employeeNumbers" name="employeeNumbers" readonly="%{readOnly}" />
					<span class="help-block">Separated with comma.</span>
				</div>
			</div>
			<!-- 
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.attendanceDate"/></label>
				<div class="col-sm-3">
					<div class="input-group">
	    			<s:textfield cssClass="datePicker form-control" id="fromAttendanceDate" name="fromAttendanceDate" readonly="%{readOnly}" placeholder="dd mmm yyyy"/><label for="date-picker-2" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
	    		</div>
		    </div>
		    <div class="col-sm-3">
					<div class="input-group">
	    			<s:textfield cssClass="datePicker form-control" id="toAttendanceDate" name="toAttendanceDate" readonly="%{readOnly}" placeholder="dd mmm yyyy"/><label for="date-picker-2" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
	    		</div>
		    </div>
		  </div>
		  -->
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.attendanceAllowance"/></label>
				<div class="col-sm-4">
					<s:select cssClass="form-control" name="attendanceAllowanceMonth" list="months" disabled="%{readOnly}" emptyOption="true" />
				</div>
				<div class="col-sm-2">
					<s:textfield cssClass="form-control" name="attendanceAllowanceYear" readonly="%{readOnly}" />
				</div>
			</div>	
				
			
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-5">
						<s:if test="(applicationSetupId==0)"><s:submit cssClass="btn btn-sml" action="%{actionMethod}" key="label.save.link"/></s:if>
		    			<s:else><s:submit cssClass="btn btn-sml" action="%{actionMethod}" key="label.update.link"/></s:else>
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
<script src="<s:url value="/js/jquery-ui-1.10.1.min.js"/>" type="text/javascript"></script>
<script src="<s:url value="/js/bootstrap.min.js"/>"></script>
<script src="<s:url value="/js/jquery.validate.min.js"/>"></script>
<script src="<s:url value="/js/additional-methods.min.js"/>" type="text/javascript"></script>
<script class="include" type="text/javascript" src="<s:url value="/js/jquery.dcjqaccordion.2.7.js"/>"></script>
<script src="<s:url value="/js/jquery.scrollTo.min.js"/>"></script>
<script src="<s:url value="/js/jquery.slimscroll.min.js"/>"></script>
<script src="<s:url value="/js/jquery.nicescroll.js"/>"></script>


<!--common script init for all pages-->
<script src="<s:url value="/js/scripts.js"/>"></script>
<script src="<s:url value="/js/paging.js"/>"></script>

<script type="text/javascript">
$(document).ready(
	function() {		
		$('.datePicker').datepicker({
	    	 dateFormat: "dd M yy",
	        beforeShow: function() {
	            setTimeout(function(){
	                $('.ui-datepicker').css('z-index', 999);
	            }, 0);
	        }
	    });
	
});
</script>


</body>
</html>



