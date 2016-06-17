package com.deniel.system;

import com.deniel.system.domain.Order;
import com.deniel.system.ui.OrderService;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {
    private OrderService orderService = new OrderService();
    private static final String WEBINF = "/WEB-INF/jsp/{0}.jsp";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String adress = req.getRequestURI().substring(req.getContextPath().length());

        if (adress.equals("/")) {
            getServletContext().getRequestDispatcher(MessageFormat.format(WEBINF, "hello")).forward(req, resp);
        } else if (adress.equals("/getallorders")) {
            List<Order> orderList = orderService.getAll();
            req.setAttribute("orderList", orderList);
            getServletContext().getRequestDispatcher(MessageFormat.format(WEBINF, "getallorders")).forward(req, resp);
        } else {
            resp.sendRedirect("http://google.com");
        }
    }
}