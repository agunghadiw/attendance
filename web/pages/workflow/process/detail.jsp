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
			<s:hidden name="processId" id="processId"/>
			<s:hidden name="userId" id="userId"/>
			<s:hidden name="previousPage" />
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:hidden name="createBy"/>
			<s:hidden name="changeBy"/>
			<s:hidden name="subaction"/>
			<s:hidden name="selectedTab"/>
			<s:hidden name="previousProcessId"/>
			<s:hidden name="oldPrevProcessId"/>
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
                          <s:text name="label.currentProcess"/>
                      </a>
                  </li>
                  <s:if test="(selectedTab==1)"><li class="active"></s:if><s:else><li class=""></s:else>
                      <a data-toggle="tab" href="#tab-2">
                          <i class="fa fa-envelope-o"></i>
                          <s:text name="label.previousProcess"/>
                      </a>
                  </li>
              </ul>
          </header>
          <div class="panel-body">
              <div class="tab-content">
                  <s:if test="(selectedTab==0)"><div id="tab-1" class="tab-pane active"></s:if><s:else><div id="tab-1" class="tab-pane"></s:else>
                  	<div class="form-group">
											<label class="col-sm-3 control-label"><s:text name="label.workflow"/></label>
											<div class="col-sm-5">
									    		<s:select cssClass="form-control" list="workflowList" id="workflowId" name="workflowId" listKey="workflowId" listValue="workflowSchemaModule" headerKey="" headerValue="Select below" disabled="%{readOnly}"/>
									    </div>
									  </div>
									  <div class="form-group">
											<label class="col-sm-3 control-label"><s:text name="label.userGroup"/>/<s:text name="label.user"/></label>
											<div class="col-sm-3">
									    		<s:select cssClass="form-control" list="userGroupList" name="userGroupId" listKey="lookupId" listValue="name" headerKey="" headerValue="Select below" disabled="%{readOnly}"/>
									    </div>
									    <div class="col-sm-3">
								    		<s:textfield cssClass="form-control" id="userName" name="userName" readonly="%{readOnly}" />
								    	</div>
									  </div>
									  <div class="form-group">
											<label class="col-sm-3 control-label"><s:text name="label.name"/></label>
											<div class="col-sm-6">
									    		<s:textfield cssClass="form-control" id="name" name="name" readonly="%{readOnly}" />
									    	</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label"><s:text name="label.isActive"/></label>
											<div class="col-sm-6">
									    		<s:checkbox id="active" name="active" disabled="%{readOnly}" />
									    	</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label"><s:text name="label.permission"/></label>
											<div class="col-sm-6">
									    	<s:optiontransferselect cssClass="form-control" cssStyle="overflow-x:scroll;width:300px;height:300px;overflow: -moz-scrollbars-horizontal;" doubleCssClass="form-control" doubleCssStyle="overflow-x:scroll;width:300px;height:300px;overflow: -moz-scrollbars-horizontal;" size="20" leftTitle="Available" rightTitle="Choosen" name="availablePermissionIds" list="availablePermissionList" listKey="permissionId" listValue="parentPermissionName" multiple="true" doubleName="choosenPermissionIds" doubleList="choosenPermissionList" doubleListKey="permissionId" doubleListValue="parentPermissionName" disabled="%{readOnly}" doubleDisabled="%{readOnly}" />	
									    	</div>
										</div>                
                  </div>
                  
                  <s:if test="(selectedTab==1)"><div id="tab-2" class="tab-pane active"></s:if><s:else><div id="tab-2" class="tab-pane"></s:else>
                  	<div class="table-responsive">
											<table class="table table-bordered table-striped">
												<thead>
									        <tr>
									        	<th>&nbsp;</th>
									          <th><s:text name="label.previousProcess"/></th>
									          <th><s:text name="label.previousProcessState"/></th>
									        </tr>
								        </thead>
<% int i = 0; %>
								        <tbody>
							            <s:iterator value="process.previousProcesses" status="sequence">
							            <tr>
								            <td><%=++i%>.</td>
								            <td><s:property value="prevProcess"/></td>
								            <td><s:property value="prevProcessState"/></td>
								            
							            </tr>
							            </s:iterator>
							            
							            
							            
							        	</tbody>
                  		</table>
                  	</div>
                  </div>
              </div>
          </div>
      </section>
      <!--tab nav end-->
			
			
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-5">
		    		<input type="button" class="btn btn-sml" value="<s:text name="label.edit.link"/>" onclick="window.location.href=('<s:url value="ProcessAction_edit.action"/>?processId=<s:property value="processId"/>');"/>&nbsp;
					<input type="button" class="btn btn-sml" value="<s:text name="label.delete.link"/>" onclick="window.location.href=('<s:url value="ProcessAction_delete.action"/>?processId=<s:property value="processId"/>');"/>&nbsp;
					<input type="button" class="btn btn-sml" value="<s:text name="label.back.link"/>" onclick="window.location.href=('<s:url value="ProcessAction_partialList.action"/>');"/>
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
		
		$('#workflowId').change(function(event) {
	      var workflowId = $("select#workflowId").val();
	      var processId = $("select#processId").val();
	      $.getJSON('/soap/ProcessJsonAction_previousProcess.action', {
	    	  amusementTypeId : amusementTypeId,
	    	  amusementTypeId : amusementTypeId
	      }, function(jsonResponse) {
	        var select = $('#amusementCategoryId');
	        select.find('option').remove();
	        $('<option>').val('').text('Select below').appendTo(select);
	        $.each(jsonResponse.amusementCategories, function() {
	          $('<option>').val(this['lookupId']).text(this['name']).appendTo(select);
	        });
	      });
	    });
	
});
</script>


</body>
</html>
