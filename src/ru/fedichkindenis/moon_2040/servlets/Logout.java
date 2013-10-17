package ru.fedichkindenis.moon_2040.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 17.10.13
 * Time: 23:22
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        request.getSession().invalidate();
        try {
            response.sendRedirect(request.getContextPath() + "/moon_2040/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{

        doPost(request, response);
    }

}
