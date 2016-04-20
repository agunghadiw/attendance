<% 
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%
//response.sendRedirect("https://"+request.getServerName()+":443"+request.getContextPath()+"/basic/UserAction_home.action");
response.sendRedirect("http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/basic/UserAction_home.action");

%>
