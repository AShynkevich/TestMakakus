package com.deniel.system;

import com.deniel.system.controllers.OrderController;

import java.io.IOException;
import java.text.MessageFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
    private static final String WEBINF_FMT = "/WEB-INF/jsp/{0}.jsp";
    private OrderController orderController = new OrderController();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getRequestURI().substring(req.getContextPath().length());

        if (address.equals("/")) {
            getServletContext().getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "hello")).forward(req, resp);
        } else if (address.contains("/order/")) {
            orderController.performRequest(req, resp, address);
        } else {
            resp.sendRedirect("http://google.com");
        }
    }
}