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

    <title>Login</title>

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

  <body class="login-body">

    <div class="container">

      <s:form focusElement="userName" id="hrForm" cssClass="form-signin">
			<s:hidden name="previousPage" />
			<s:hidden name="actionMethod" value="%{actionMethod}"/>
			<s:token/>
        <h2 class="form-signin-heading">Aplikasi Tukin</h2>
        <div class="login-wrap">
        		
        		<s:if test="(hasActionErrors() || hasFieldErrors() || hasActionMessages() )">
    					<div class="alert alert-danger alert-dismissable">
    					<i class="fa fa-ban"></i>
            		<s:actionerror/><s:fielderror></s:fielderror><s:actionmessage/>
        			</div>
        		</s:if>
        
            <div class="user-login-info">
            		<s:textfield name="userName" readonly="%{readOnly}" cssClass="form-control" required="true" placeholder="User ID"/>
                <s:password name="userPass" readonly="%{readOnly}" cssClass="form-control" placeholder="Password" />
            </div>
            <label class="checkbox">

								<!-- 
                <input type="checkbox" value="remember-me"> Remember me

                <span class="pull-right">
                    <a data-toggle="modal" href="#myModal"> Lupa Password?</a>

                </span>
                -->
                
            </label>
            <s:submit cssClass="btn btn-lg btn-login btn-block" action="%{actionMethod}" key="label.login.link"/>
						
						<!-- 
            <div class="registration">
                Don't have an account yet?
                <a class="" href="registration.html">
                    Create an account
                </a>
            </div>
            -->

        </div>

          <!-- Modal -->
          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
              <div class="modal-dialog">
                  <div class="modal-content">
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">Forgot Password ?</h4>
                      </div>
                      <div class="modal-body">
                          <p>Enter your e-mail address below to reset your password.</p>
                          <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">

                      </div>
                      <div class="modal-footer">
                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                          <button class="btn btn-success" type="button">Submit</button>
                      </div>
                  </div>
              </div>
          </div>
          <!-- modal -->

      </s:form>

    </div>



    <!-- Placed js at the end of the document so the pages load faster -->

    <!--Core js-->
    <script src="<s:url value="/js/jquery.js"/>"></script>
    <script src="<s:url value="/js/bootstrap.min.js"/>"></script>
    
    
    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script type="text/javascript" src="<s:url value="/js/jquery.backstretch.min.js"/>"></script>
    <script>
        $.backstretch("<s:url value="/images/kemensos_building.jpg"/>", {speed: 500});
    </script>

  </body>
</html>

