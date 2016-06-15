package com.deniel.system;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SomeJSP extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String someString = "I don't have any idea what to print here...";
        req.setAttribute("someString", someString);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/somejsp.jsp").forward(req, resp);
    }
}