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
			<s:hidden name="echelonId"/>
			<s:hidden name="previousPage" />
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:hidden name="createBy"/>
			<s:hidden name="changeBy"/>
			<s:hidden name="subaction"/>
			<s:hidden name="oldNextEchelonId"/>
			<s:hidden name="selectedTab"/>
			<s:token></s:token>
			<s:if test="(hasActionErrors() || hasFieldErrors() || hasActionMessages() )">
		   	<div class="col-xs-12 alert alert-danger alert-dismissable">
		   		<i class="fa fa-ban"></i>
		        <s:actionerror/><s:fielderror></s:fielderror><s:actionmessage/>
		    </div>
		    </s:if>
		    
		    <!--tab nav start-->
      <section class="panel">
          <header class="panel-heading tab-bg-dark-navy-blue">
              <ul class="nav nav-tabs">
                  <s:if test="(selectedTab==0)"><li class="active"></s:if><s:else><li class=""></s:else>
                      <a data-toggle="tab" href="#tab-1">
                          <i class="fa fa-user"></i>
                          <s:text name="label.main"/>
                      </a>
                  </li>
                  <s:if test="(selectedTab==1)"><li class="active"></s:if><s:else><li class=""></s:else>
                      <a data-toggle="tab" href="#tab-2">
                          <i class="fa fa-envelope-o"></i>
                          <s:text name="label.approval"/>
                      </a>
                  </li>
              </ul>
          </header>
          <div class="panel-body">
              <div class="tab-content">
                  <s:if test="(selectedTab==0)"><div id="tab-1" class="tab-pane active"></s:if><s:else><div id="tab-1" class="tab-pane"></s:else>
                  	<div class="form-group">
											<label class="col-sm-3 control-label"><s:text name="label.level"/></label>
											<div class="col-sm-1">
									    		<s:textfield cssClass="form-control" id="level" name="level" readonly="%{readOnly}" />
									    	</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label"><s:text name="label.name"/></label>
											<div class="col-sm-6">
									    		<s:textfield cssClass="form-control" id="name" name="name" readonly="%{readOnly}" />
									    	</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label"><s:text name="label.position"/></label>
											<div class="col-sm-7">
									    		<s:optiontransferselect cssClass="form-control" cssStyle="overflow-x:scroll;width:300px;height:300px;overflow: -moz-scrollbars-horizontal;" doubleCssClass="form-control" doubleCssStyle="overflow-x:scroll;width:300px;height:300px;overflow: -moz-scrollbars-horizontal;" size="20"  leftTitle="Available" rightTitle="Choosen" name="availablePositionIds" list="availablePositions" listKey="positionId" listValue="name" multiple="true" doubleName="choosenPositionIds" doubleList="choosenPositions" doubleListKey="positionId" doubleListValue="name" disabled="%{readOnly}" doubleDisabled="%{readOnly}"/>
									    	</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label"><s:text name="label.degree"/></label>
											<div class="col-sm-7">
									    		<s:optiontransferselect cssClass="form-control" cssStyle="overflow-x:scroll;width:300px;height:300px;overflow: -moz-scrollbars-horizontal;" doubleCssClass="form-control" doubleCssStyle="overflow-x:scroll;width:300px;height:300px;overflow: -moz-scrollbars-horizontal;" size="20"  leftTitle="Available" rightTitle="Choosen" name="availableDegreeIds" list="availableDegrees" listKey="degreeId" listValue="name" multiple="true" doubleName="choosenDegreeIds" doubleList="choosenDegrees" doubleListKey="degreeId" doubleListValue="name" disabled="%{readOnly}" doubleDisabled="%{readOnly}"/>
									    	</div>
										</div>
                  </div>
                  
                  <s:if test="(selectedTab==1)"><div id="tab-2" class="tab-pane active"></s:if><s:else><div id="tab-2" class="tab-pane"></s:else>
                  	<div class="table-responsive">
											<table class="table table-bordered table-striped">
												<thead>
									        <tr>
									        	<th>&nbsp;</th>
									          <th><s:text name="label.nextEchelon"/></th>
									          <th><s:text name="label.sequence"/></th>
									          <th>&nbsp;</th>
									        </tr>
								        </thead>
<% int i = 0; %>
								        <tbody>
							            <s:iterator value="echelon.echelonApprovalFlows" status="sequence">
							            <tr>
								            <td><%=++i%>.</td>
								            <td><s:property value="nextEchelon.levelName"/></td>
								            <td><s:property value="sequence"/></td>
								            <td>
								            	<a href="<s:url value="EchelonAction_add.action"/>?echelonId=<s:property value="echelonId"/>&echelonApprovalFlowId=<s:property value="echelonApprovalFlowId"/>&nextEchelonId=<s:property value="nextEchelon.echelonId"/>&oldNextEchelonId=<s:property value="nextEchelon.echelonId"/>&selectedTab=1&subaction=EDIT"><i class="fa fa-pencil" title="Edit"></i></a>&nbsp;
							                <a href="<s:url value="EchelonAction_add.action"/>?echelonId=<s:property value="echelonId"/>&echelonApprovalFlowId=<s:property value="echelonApprovalFlowId"/>&nextEchelonId=<s:property value="nextEchelon.echelonId"/>&oldNextEchelonId=<s:property value="nextEchelon.echelonId"/>&selectedTab=1&subaction=REMOVE"><i class="fa fa-trash-o" title="Remove"></i></a>
								            </td>
							            </tr>
							            </s:iterator>
							            
							            <tr>
							            	<td>&nbsp;</td>
							            	<td><s:select cssClass="form-control" list="nextEchelons" id="nextEchelonId" name="nextEchelonId" listKey="echelonId" listValue="levelName" headerKey="" headerValue="Select below" disabled="%{readOnly}"/></td>
							            	<td><s:textfield cssClass="form-control" id="sequenceFlow" name="sequenceFlow" readonly="%{readOnly}" tooltip=""/></td>
							            	<td><s:submit cssClass="btn btn-sml" action="%{actionMethod}" key="label.add.link" onclick="this.form.subaction.value='ADD';selectAllOptions(document.getElementById('hrForm_availableDegreeIds'));selectAllOptions(document.getElementById('hrForm_choosenDegreeIds'));selectAllOptions(document.getElementById('hrForm_availablePositionIds'));selectAllOptions(document.getElementById('hrForm_choosenPositionIds'));"/></td>
							            </tr>
							            
							        	</tbody>
                  		</table>
                  	</div>
                  </div>
                  
              </div>
           </div>
        </section>
		    
		    
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-5">
		    		<s:submit cssClass="btn btn-sml" action="%{actionMethod}" onclick="selectAllOptions(document.getElementById('hrForm_availableDegreeIds'));selectAllOptions(document.getElementById('hrForm_choosenDegreeIds'));selectAllOptions(document.getElementById('hrForm_availablePositionIds'));selectAllOptions(document.getElementById('hrForm_choosenPositionIds'));this.form.subaction.value='SAVE_ALL';" key="label.save.link" />
						<input type="button" class="btn btn-sml" value="<s:text name="label.cancel.link"/>" onclick="window.location.href=('<s:url value="EchelonAction_cancel.action"/>');"/>
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
				name: {
					required: true
				},
			}
		});
	
});
</script>


</body>
</html>
