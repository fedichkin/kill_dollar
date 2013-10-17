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
    if(session.getAttribute("person_uid") == null){

        response.sendRedirect(request.getContextPath() + "/moon_2040/login.jsp");
    }
    else{
        response.sendRedirect(request.getContextPath() + "/moon_2040/list_game.jsp");
    }
%>