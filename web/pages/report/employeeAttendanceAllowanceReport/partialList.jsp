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
			
			
			<s:form cssClass="form-horizontal" role="form" id="hrForm">
			<s:hidden name="start"/>
			<s:hidden name="count"/>
			<s:hidden name="orderBy"/>
			<s:hidden name="ascDesc"/>
			<s:hidden name="previousPage"/>
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:token></s:token>
				<div class="form-group">
			    <label class="col-sm-2 control-label"><s:text name="label.branch"/></label>
			    <div class="col-sm-5">
			    	<s:if test="(branchs.size()>1)">
			      <s:select list="branchs" cssClass="form-control" id="branchId" name="branchId" headerKey="" headerValue="Select below" listKey="branchId" listValue="name"/>
			    	</s:if>
			    	<s:else>
			    	<s:select list="branchs" cssClass="form-control" id="branchId" name="branchId" listKey="branchId" listValue="name"/>
			    	</s:else>
			    </div>
				</div>
		  	<div class="form-group">
			    <label class="col-sm-2 control-label"><s:text name="label.periode"/></label>
			    <div class="col-sm-3">
			      <s:select cssClass="form-control" name="month" list="months" emptyOption="true" />
			    </div>
			    <div class="col-sm-2">
			      <s:textfield cssClass="form-control" name="year" />
			    </div>
			    <div class="col-sm-5">
			    	<s:submit value="%{getText('label.search.link')}" cssClass="btn btn-sml"/>
			    </div>
			</div>
		  	<div class="row">
      			<div class="col-md-5 text-left"><s:property value="%{pager}" escape="false"/></div>
      			<div class="col-md-1">
		      		<s:textfield cssClass="form-control input-sm m-bot15" name="gotoPage"  onchange="gotoPager();" placeHolder="Page"/>
		      	</div>
      			<div class="col-md-6 text-right"><s:property value="%{pagerItem}" escape="false"/></div>
      		</div>
			<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
			        <tr>
			        	<th>&nbsp;</th>
			          <th><s:text name="label.number"/></th>
			          <th><s:text name="label.fullName"/></th>
			          <th><s:text name="label.position"/></th>
			          <th><s:text name="label.rating"/></th>
			          <th><s:text name="label.grade"/></th>
			          <th><s:text name="label.attendanceNote"/></th>
			          <th><s:text name="label.status"/></th>
			          <th><s:text name="label.performanceAchievement"/></th>
			          <th><s:text name="label.fromDate"/></th>
			          <th><s:text name="label.toDate"/></th>
			          <s:iterator value="leaveDeductions">
			          <th><a href="#" data-toggle="tooltip" data-original-title="<s:property value="description"/>"><s:property value="code"/></a></th>
			          </s:iterator>
			          <th><s:text name="label.attendanceAllowance"/></th>
			          <th><s:text name="label.deductionAmount"/></th>
			          <th><s:text name="label.loanAmount"/></th>
			          <th><s:text name="label.taxPph"/></th>
			          <th><s:text name="label.total"/></th>
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
		            <s:iterator value="reports" status="sequence">
		            <tr>
			            <td><%=++i%>.</td>
			            <td><s:property value="number"/></td>
			            <td><s:property value="fullName"/></td>
			            <td><s:property value="position"/></td>
			            <td><s:property value="rating"/></td>
			            <td><s:property value="grade"/></td>
			            <td><s:property value="attendanceNote"/></td>
			            <td><s:property value="status"/></td>
			            <td><s:property value="performanceAchievement"/>%</td>
			            <td><s:date name="fromDate" format="dd MMM yyyy"/></td>
			            <td><s:date name="toDate" format="dd MMM yyyy"/></td>
			            <s:iterator value="leaveDeductions">
			            <td>
			            	<s:iterator value="employeeAttendanceAllowanceMonthlyDetails" >
						      	<s:if test="(code.equalsIgnoreCase(leaveTypeCode))"><s:property value="total"/></s:if>
						      	</s:iterator>
			            </td>
			            </s:iterator>
			            <td align="right"><s:property value="%{getText('format.number',{attendanceAllowance})}" /></td>
			            <td align="right"><s:property value="%{getText('format.number',{deductionAmount})}" /></td>
			            <td align="right"><s:property value="%{getText('format.number',{loanAmount})}" /></td>
			            <td align="right"><s:property value="%{getText('format.number',{taxPph})}" /></td>
			            <td align="right"><s:property value="%{getText('format.number',{totalWithLoan})}" /></td>
		            </tr>
		            </s:iterator>
		        </tbody>
		  	</table>
		  	</div>
	  		<div class="row">
	      	<div class="col-md-5 text-left"><s:property value="%{pager}" escape="false"/></div>
		      	<div class="col-md-1">
		      		<s:textfield cssClass="form-control input-sm m-bot15" name="gotoPage2" onchange="gotoPager2();" placeHolder="Page"/>
		      	</div>
		      	<div class="col-md-6 text-right"><s:property value="%{pagerItem}" escape="false"/></div>
	      	</div>
  			</s:form>
  			<div class="col-sm-offset-6 col-sm-6">
		     	<input class="btn btn-sml" type="button" onclick="window.open('<s:url value="/report/EmployeeAttendanceAllowanceReportAction_excelList.action"/>?month=<s:property value="month"/>&year=<s:property value="year"/>&branchId=<s:property value="branchId"/>','gg','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600');return false;" value="<s:text name="label.printed.link"/>">&nbsp;
		     	<input class="btn btn-sml" type="button" onclick="window.open('<s:url value="/report/EmployeeAttendanceAllowanceReportAction_excelDetail.action"/>?month=<s:property value="month"/>&year=<s:property value="year"/>&branchId=<s:property value="branchId"/>','gg','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=800,height=600');return false;" value="<s:text name="label.detailPrinted.link"/>">
		  	
		  	</div>
			
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
		
		$('[data-toggle="tooltip"]').tooltip({
	        placement : 'top'
	    });
	
});
</script>


</body>
</html>