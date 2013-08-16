<%--
/**
 * Created with IntelliJ IDEA.
 * User: fedichkin_du
 * Date: 05.07.13
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />

<%
    if(request.getUserPrincipal() == null){
%>
     <jsp:forward page="login.jsp"></jsp:forward>
<%
    }
    else{
%>
    <jsp:forward page="list_game.jsp"></jsp:forward>
<%
    }
%>