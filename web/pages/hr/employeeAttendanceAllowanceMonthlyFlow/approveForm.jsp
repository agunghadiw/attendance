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
    
    <link href="<s:url value="/css/timepicker.css"/>" rel="stylesheet">
    
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
			<s:hidden name="employeeAttendanceAllowanceMonthlyId"/>
			<s:hidden name="previousPage" />
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:hidden name="createBy"/>
			<s:hidden name="changeBy"/>
			<s:hidden name="employeeId" id="employeeId"/>
			<s:hidden name="lastStatus"/>
			<s:hidden name="gradeId"/>
			<s:hidden name="branchId"/>
			<s:hidden name="degreeId"/>
			<s:hidden name="ratingId"/>
			<s:hidden name="positionId"/>
			<s:token></s:token>
			<s:if test="(hasActionErrors() || hasFieldErrors() || hasActionMessages() )">
		   	<div class="col-xs-12 alert alert-danger alert-dismissable">
		   		<i class="fa fa-ban"></i>
		        <s:actionerror/><s:fielderror></s:fielderror><s:actionmessage/>
		    </div>
		    </s:if>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.employee"/></label>
				<div class="col-sm-2">
	    		<s:textfield cssClass="form-control" id="number" name="number" readonly="true" />
	    	</div>
	    	<div class="col-sm-4">
	    		<s:textfield cssClass="form-control" id="fullName" name="fullName" readonly="true" />
	    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.periode"/></label>
				<div class="col-sm-3">
		    		<s:textfield cssClass="form-control" id="monthInText" name="monthInText" readonly="true" />
		    </div>
		    <div class="col-sm-2">
		    		<s:textfield cssClass="form-control" id="year" name="year" readonly="true" />
		    </div>
	  	</div>
	  	<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.grade"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" id="grade" name="grade" readonly="true" />
		    </div>
	  	</div>
	  	<!-- 
	  	<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.branch"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" id="branch" name="branch" readonly="true" />
		    </div>
	  	</div>
	  	-->
	  	<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.rating"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" id="rating" name="rating" readonly="true" />
		    </div>
	  	</div>
	  	<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.position"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" id="position" name="position" readonly="true" />
		    </div>
	  	</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.leaveDate"/></label>
				<div class="col-sm-3">
	    		<div class="input-group">
	    			<s:textfield cssClass="form-control" id="fromDate" name="fromDate" readonly="true" placeholder="dd mmm yyyy"/><label for="date-picker-2" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
	    		</div>
	    	</div>
	    	<div class="col-sm-3">
	    		<div class="input-group">
	    			<s:textfield cssClass="form-control" id="toDate" name="toDate" readonly="true" placeholder="dd mmm yyyy"/><label for="date-picker-2" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
	    		</div>
	    	</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.attendanceNote"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" id="attendanceNote" name="attendanceNote" readonly="true"  />
		    </div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.attendanceNote"/></label>
				<div class="col-sm-9">
		    		
		 <div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
	        <tr>
    				<s:iterator value="leaveDeductions">
	          <th><s:property value="code" /></th>
	          </s:iterator>
	        </tr>
	      </thead>
	      <tbody>
		      <tr>
		      	<s:iterator value="leaveDeductions" >
		      	<td>
		      	<s:iterator value="employeeAttendanceAllowanceMonthlyDetails" >
		      	<s:if test="(code.equalsIgnoreCase(leaveTypeCode))"><s:property value="total"/></s:if>
		      	</s:iterator>
		      	</td>
            </s:iterator>
			    </tr>
			  </tbody>
			</table>
		</div>    
		    		
		    </div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.status"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" id="status" name="status" readonly="true"  />
		    </div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><b><s:text name="label.performanceAchievement"/></b></label>
		    <div class="col-sm-3">
					<div class="input-group">
		    		<s:textfield cssClass="form-control" id="performanceAchievement" name="performanceAchievement" readonly="%{readOnly}" placeHolder="Procentage" />
		    		<span class="input-group-addon"><b>%</b></span>
		    	</div>
		    </div>
			</div>
			
			
			
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-5">
		    		<s:submit cssClass="btn btn-sml" action="%{actionMethod}" key="label.approve.link" onclick="this.form.lastStatus.value='APPROVED';" />
		    		<s:submit cssClass="btn btn-sml" action="%{actionMethod}" key="label.reject.link" onclick="this.form.lastStatus.value='REJECT';"/>
		    		<input type="button" class="btn btn-sml" value="<s:text name="label.back.link"/>" onclick="window.location.href=('<s:url value="EmployeeLeaveFlowAction_cancel.action"/>');"/>
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
<script src="<s:url value="/js/bootstrap-timepicker.js"/>"></script>

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

<script type="text/javascript">
$(document).ready(function() {		
		$('.datePicker').datepicker({
	    	 dateFormat: "dd M yy",
	        beforeShow: function() {
	            setTimeout(function(){
	                $('.ui-datepicker').css('z-index', 999);
	            }, 0);
	        }
	    });
		
		//timepicker start
		$('.timepicker-default').timepicker();


		$('.timepicker-24').timepicker({
		    autoclose: true,
		    minuteStep: 1,
		    showSeconds: false,
		    showMeridian: false,
		    defaultTime: false
		});

		//timepicker end
		
		$('#hrForm').validate({
			rules: {
				attendanceDate: {
					required: true
				},
				startTime: {
					required: true
				},
				number: {
					required: true
				},
				fullName: {
					required: true
				},
			}
		});
	
});
</script>


</body>
</html>