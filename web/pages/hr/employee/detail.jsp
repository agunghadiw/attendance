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
			<s:hidden name="employeeId"/>
			<s:hidden name="previousPage" />
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:hidden name="createBy"/>
			<s:hidden name="changeBy"/>
			<s:hidden name="userId"/>
			<s:token></s:token>
			<s:if test="(hasActionErrors() || hasFieldErrors() || hasActionMessages() )">
   			<div class="col-xs-12 alert alert-danger alert-dismissable">
   				<i class="fa fa-ban"></i>
        		<s:actionerror/><s:fielderror></s:fielderror><s:actionmessage/>
		    </div>
		    </s:if>
		  <div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.number"/></label>
				<div class="col-sm-3">
	    		<s:textfield cssClass="form-control" name="number" readonly="%{readOnly}" />
	    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.fullName"/></label>
				<div class="col-sm-6">
	    		<s:textfield cssClass="form-control" name="fullName" readonly="%{readOnly}" />
	    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.attendanceId"/></label>
				<div class="col-sm-3">
	    		<s:textfield cssClass="form-control" id="attendanceId" name="attendanceId" readonly="%{readOnly}" />
	    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.gender"/></label>
		    	<div class="col-sm-5">
		    		<s:radio cssClass="form-text" id="gender" name="gender" list="genders" disabled="%{readOnly}" />
		    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.birthDate"/></label>
		    	<div class="col-sm-3">
		    		<div class="input-group">
		    			<s:textfield cssClass="datePickerBirthDate form-control" id="birthDate" name="birthDate" readonly="%{readOnly}" placeholder="dd mmm yyyy"/><label for="date-picker-2" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
		    		</div>
		    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.birthPlace"/></label>
				<div class="col-sm-6">
	    		<s:textfield cssClass="form-control" name="birthPlace" readonly="%{readOnly}" />
	    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.joinDate"/></label>
		    	<div class="col-sm-3">
		    		<div class="input-group">
		    			<s:textfield cssClass="datePicker form-control" id="joinDate" name="joinDate" readonly="%{readOnly}" placeholder="dd mmm yyyy"/><label for="date-picker-2" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
		    		</div>
		    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.resignDate"/></label>
		    	<div class="col-sm-3">
		    		<div class="input-group">
		    			<s:textfield cssClass="datePicker form-control" id="resignDate" name="resignDate" readonly="%{readOnly}" placeholder="dd mmm yyyy"/><label for="date-picker-2" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
		    		</div>
		    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.branch"/></label>
				<div class="col-sm-6">
	    		<s:select cssClass="form-control" list="branchs" name="branchId" listKey="branchId" listValue="name" headerKey="" headerValue="Select below" disabled="%{readOnly}"/>
	    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.religion"/></label>
				<div class="col-sm-5">
		    		<s:select cssClass="form-control" list="religions" name="religionId" listKey="id" listValue="name" headerKey="" headerValue="Select below" disabled="%{readOnly}"/>
		    	</div>
	  	</div>
	  	<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.rating"/></label>
				<div class="col-sm-5">
		    		<s:select cssClass="form-control" list="ratings" name="ratingId" listKey="ratingId" listValue="name" headerKey="" headerValue="Select below" disabled="%{readOnly}"/>
		    	</div>
	  	</div>
	  	<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.position"/></label>
				<div class="col-sm-5">
		    		<s:select cssClass="form-control" list="positions" id="positionId" name="position.positionId" listKey="positionId" listValue="name" headerKey="" headerValue="Select below" disabled="%{readOnly}"/>
		    	</div>
	  	</div>
	  	<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.degree"/></label>
				<div class="col-sm-5">
		    		<s:select cssClass="form-control" list="degrees" id="degreeId" name="degreeId" listKey="degreeId" listValue="name" headerKey="" headerValue="Select below" disabled="%{readOnly}"/>
		    	</div>
	  	</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.specialWorkingShift"/></label>
				<div class="col-sm-6">
		    		<s:select cssClass="form-control" list="workingShifts" id="specialWorkingShiftId" name="specialWorkingShiftId" listKey="workingShiftId" listValue="name" headerKey="" headerValue="Select below" disabled="%{readOnly}"/>
		    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.employeeType"/></label>
				<div class="col-sm-6">
		    		<s:radio list="employeeTypes" id="employeeType" name="employeeType" disabled="%{readOnly}"/>
		    </div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.isLecture"/></label>
				<div class="col-sm-6">
		    		<s:checkbox name="lecture" disabled="%{readOnly}" />&nbsp;<s:text name="label.yes"/>
		    </div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.lectureAllowance"/></label>
				<div class="col-sm-4">
	    		<s:textfield cssClass="form-control" id="lectureAllowance" name="lectureAllowance" readonly="%{readOnly}" />
	    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.bankAccountNumber"/></label>
				<div class="col-sm-4">
	    		<s:textfield cssClass="form-control" id="bankAccountNumber" name="bankAccountNumber" readonly="%{readOnly}" />
	    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.bankAccountName"/></label>
				<div class="col-sm-6">
	    		<s:textfield cssClass="form-control" id="bankAccountName" name="bankAccountName" readonly="%{readOnly}" />
	    	</div>
			</div> 
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.createBy"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" name="employee.createBy" readonly="%{readOnly}" />
		    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.createOn"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" name="employee.createOn" readonly="%{readOnly}" />
		    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.changeBy"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" name="employee.changeBy" readonly="%{readOnly}" />
		    	</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-3 control-label"><s:text name="label.changeOn"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" name="employee.changeOn" readonly="%{readOnly}" />
		    	</div>
			</div>
			
			
			<div class="form-group">
					<div class="col-sm-offset-3 col-sm-5">
			    		<input type="button" class="btn btn-sml" value="<s:text name="label.edit.link"/>" onclick="window.location.href=('<s:url value="EmployeeAction_edit.action"/>?employeeId=<s:property value="employeeId"/>');"/>&nbsp;
						<input type="button" class="btn btn-sml" value="<s:text name="label.delete.link"/>" onclick="window.location.href=('<s:url value="EmployeeAction_delete.action"/>?employeeId=<s:property value="employeeId"/>');"/>&nbsp;
						<input type="button" class="btn btn-sml" value="<s:text name="label.back.link"/>" onclick="window.location.href=('<s:url value="EmployeeAction_partialList.action"/>');"/>
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
		
		$('.datePickerBirthDate').datepicker({
	    	 dateFormat: "dd M yy",
	    	 changeYear: true,
	        yearRange: "-100:now",
	        beforeShow: function() {
	            setTimeout(function(){
	                $('.ui-datepicker').css('z-index', 999);
	            }, 0);
	        }
	    });
		
		$('#hrForm').validate({
			rules: {
				number: {
					required: true
				},
				fullName: {
					required: true
				},
			}
		});
		
		$('#positionId').change(function(event) {
		      var positionId = $("select#positionId").val();
		      //alert('positionId = '+positionId );
		      $.getJSON('../soap/PositionJsonAction_degreeByPosition.action', {
		    	  positionId : positionId
		      }, function(jsonResponse) {
		        var select = $('#degreeId');
		        select.find('option').remove();
		        $('<option>').val('').text('Select below').appendTo(select);
		        $.each(jsonResponse.degrees, function() {
		          $('<option>').val(this['degreeId']).text(this['name']).appendTo(select);
		        });
		      });
		    });

	
});
</script>


</body>
</html>


