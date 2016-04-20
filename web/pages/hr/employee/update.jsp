<%@page import="com.opensymphony.xwork2.ActionContext"%>
<% response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><s:text name="label.index"/></title>
    <!-- Bootstrap Core CSS -->
    <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="<s:url value="/metisMenu/dist/metisMenu.min.css"/>" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<s:url value="/css/sb-admin-2.css"/>" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<s:url value="/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <s:include value="/common/header.jsp"/>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h4 class="page-header"><s:property value="#session['permission_name']"/></h4>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
			
			
			<s:form cssClass="form-horizontal" id="hrForm">
			<s:hidden name="previousPage" />
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:hidden name="createBy"/>
			<s:hidden name="changeBy"/>
			<s:token></s:token>
			<p class="text-center">Data employee been saved/updated.</p>
			<p class="text-center">Thank you.</p>
			<br>
			<br>
			<p class="text-center">
				<input type="button" class="btn btn-sml" value="<s:text name="label.back.link"/>" onclick="window.location.href=('<s:url value="EmployeeAction_partialList.action"/>');"/>
  			</p>
  			</s:form>
			
			
						</div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="<s:url value="/js/jquery.min.js"/>"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<s:url value="/js/bootstrap.min.js"/>"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="<s:url value="/metisMenu/dist/metisMenu.min.js"/>"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<s:url value="/js/sb-admin-2.js"/>"></script>

</body>

</html>


