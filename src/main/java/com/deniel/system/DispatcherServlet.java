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

public class DispatcherServlet extends HttpServlet {
    private static final String WEBINF_FMT = "/WEB-INF/jsp/{0}.jsp";
    private OrderService orderService = new OrderService();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String adress = req.getRequestURI().substring(req.getContextPath().length());

        if (adress.equals("/")) {
            getServletContext().getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "hello")).forward(req, resp);
        } else if (adress.equals("/getallorders")) {
            List<Order> orderList = orderService.getAll();
            req.setAttribute("orderList", orderList);
            getServletContext().getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "getallorders")).forward(req, resp);
        } else {
            resp.sendRedirect("http://google.com");
        }
    }
}