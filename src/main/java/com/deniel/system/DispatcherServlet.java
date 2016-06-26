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
    private static final String ORDER_PATH = "/order";
    private static final String HELLO_PATH = "/hello";
    private static final String ROOT_PATH = "/";
    private OrderController orderController = new OrderController();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getRequestURI().substring(req.getContextPath().length());

        if (ROOT_PATH.equals(address)) {
            performForward(HELLO_PATH, req, resp);
        } else if (address.contains(ORDER_PATH)) {
            orderController.performRequest(req, resp, address);
        } else {
            resp.sendRedirect("http://google.com");
        }
    }

    private void performForward (String address, HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        req.getRequestDispatcher(MessageFormat.format(WEBINF_FMT, address)).forward(req, resp);
    }
}