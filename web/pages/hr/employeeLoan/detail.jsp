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
			<s:hidden name="employeeLoanId"/>
			<s:hidden name="previousPage" />
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:hidden name="createBy"/>
			<s:hidden name="changeBy"/>
			<s:hidden name="employeeId" id="employeeId"/>
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
	    		<s:textfield cssClass="form-control" id="number" name="number" readonly="%{readOnly}" onclick="window.open('EmployeeAction_popUp.action', 'popup', 'width=600,height=480,top=100,left=100');" />
	    	</div>
	    	<div class="col-sm-4">
	    		<s:textfield cssClass="form-control" id="fullName" name="fullName" readonly="%{readOnly}" onclick="window.open('EmployeeAction_popUp.action', 'popup', 'width=600,height=480,top=100,left=100');" />
	    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.loanDate"/></label>
				<div class="col-sm-3">
	    		<div class="input-group">
	    			<s:textfield cssClass="datePicker form-control" id="loanDate" name="loanDate" readonly="%{readOnly}" placeholder="dd mmm yyyy"/><label for="date-picker-2" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
	    		</div>
	    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.loanAmount"/></label>
				<div class="col-sm-3">
		    		<s:textfield cssClass="form-control" id="loanAmount" name="loanAmount" readonly="%{readOnly}" />
		    </div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.duration"/></label>
				<div class="col-sm-1">
		    		<s:textfield cssClass="form-control" id="duration" name="duration" readonly="%{readOnly}"  />
		    </div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.installment"/></label>
				<div class="col-sm-3">
		    		<s:textfield cssClass="form-control" id="installment" name="installment" readonly="true" />
		    </div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.createBy"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" name="employeeLoan.createBy" readonly="%{readOnly}" />
		    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.createOn"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" name="employeeLoan.createOn" readonly="%{readOnly}" />
		    	</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.changeBy"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" name="employeeLoan.changeBy" readonly="%{readOnly}" />
		    	</div>
			</div>
			<div class="form-group">
				<label  class="col-sm-3 control-label"><s:text name="label.changeOn"/></label>
				<div class="col-sm-5">
		    		<s:textfield cssClass="form-control" name="employeeLoan.changeOn" readonly="%{readOnly}" />
		    	</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label"><s:text name="label.payment"/></label>
				<div class="col-sm-8">
		    		
		    		
			       	<div class="table-responsive">
								<table class="table table-bordered table-striped">
									<thead>
								      <tr>
								      	<th>&nbsp;</th>
								        <th><s:text name="label.paymentDate"/></th>
								       <th><s:text name="label.paymentAmount"/></th>
								     </tr>
								    </thead>
								<% int i = 0; %>
								    <tbody>
								       <s:iterator value="employeeLoan.employeeLoanPayments" status="sequence">
								       <tr>
								        <td><%=++i%>.</td>
								        <td><s:date name="paymentDate" format="dd MMM yyyy"/></td>
								        <td><s:property value="%{getText('format.number',{paymentAmount})}" /></td>
								       </tr>
								       </s:iterator>
								       
								   	</tbody>
			      		</table>
			      	</div>
		    		
		    </div>
			</div>
			
			<div class="form-group">
					<div class="col-sm-offset-3 col-sm-5">
						<s:if test="(employeeLoan.employeeLoanPayments.size()==0)">
			    	<input type="button" class="btn btn-sml" value="<s:text name="label.edit.link"/>" onclick="window.location.href=('<s:url value="EmployeeLoanAction_edit.action"/>?employeeLoanId=<s:property value="employeeLoanId"/>');"/>&nbsp;
						<input type="button" class="btn btn-sml" value="<s:text name="label.delete.link"/>" onclick="window.location.href=('<s:url value="EmployeeLoanAction_delete.action"/>?employeeLoanId=<s:property value="employeeLoanId"/>');"/>&nbsp;
						</s:if>
						<input type="button" class="btn btn-sml" value="<s:text name="label.back.link"/>" onclick="window.location.href=('<s:url value="EmployeeLoanAction_partialList.action"/>');"/>
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
		
		$('#hrForm').validate({
			rules: {
				level: {
					required: true
				},
				attendanceAllowance: {
					number: true
				},
				taxPph: {
					number: true,
					min:0,
					max:100, 
				},
			}
		});
		
		$("#loanAmount, #duration" ).change(function() {
			var a = 0;
			if(!isNaN($("#loanAmount").val()) && $("#loanAmount").val().length!=0) {
				a = parseFloat($("#loanAmount").val());
			}
			var b = 0;
			if(!isNaN($("#duration").val()) && $("#duration").val().length!=0) {
				b = parseFloat($("#duration").val());
			}
			a = a / b;
			$("#installment").val(a);
		});
	
});
</script>


</body>
</html>
