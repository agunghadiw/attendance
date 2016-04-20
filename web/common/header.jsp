<%@ taglib prefix="s" uri="/struts-tags"%>


<header class="header fixed-top clearfix">
<!--logo start-->
<div class="brand">

    <a href="index.jsp" class="logo">
        TUKIN
    </a>
    <div class="sidebar-toggle-box">
        <div class="fa fa-bars"></div>
    </div>
</div>
<!--logo end-->

<div class="nav notify-row" id="top_menu">
</div>





<div class="top-nav clearfix">
    <!--search & user info start-->
    <ul class="nav pull-right top-menu">
        <li>
            <input type="text" class="form-control search" placeholder=" Search">
        </li>
        <!-- user login dropdown start-->
        <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle icon-user" href="#">
                <!--<img alt="" src="images/avatar1_small.jpg">-->
                <i class="fa fa-user"></i>
                <span class="username"><s:if test="#session['user_full_name']!=null"><s:property value="#session['user_full_name']" /></s:if></span>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu extended logout">
            	<!-- 
                <li><a href="#"><i class=" fa fa-suitcase"></i>Profile</a></li>
                -->
                <li><a href="<s:url value="/basic/UserAction_changePasswordEdit.action"/>"><i class="fa fa-cog"></i> Ubah Kata Sandi</a></li>
                <li><a href="<s:url value="/basic/LogonAction_logoff.action"/>"><i class="fa fa-key"></i> Keluar</a></li>
            </ul>
        </li>
        <!-- user login dropdown end -->
        <li>
            <div class="toggle-right-box">
                <div class="fa fa-bars"></div>
            </div>
        </li>
    </ul>
    <!--search & user info end-->
</div>
</header>