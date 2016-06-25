package com.deniel.system.controllers;

import com.deniel.system.domain.Order;
import com.deniel.system.ui.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

/**
 * Created by DenielNote on 23.06.2016.
 */
public class OrderController extends HttpServlet {
    private OrderService orderService = new OrderService();
    private static final String WEBINF_FMT = "/WEB-INF/jsp/{0}.jsp";

    public void performRequest (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String adress = req.getRequestURI().substring(req.getContextPath().length());

        if (adress.equals("/order/createform")) {
            getServletContext().getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/createform")).forward(req, resp);
        } else if (adress.equals("/order/list")) {
            getallorders(req);
            getServletContext().getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/list")).forward(req, resp);
        }
    }

    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adress = req.getRequestURI().substring(req.getContextPath().length());

        if (adress.contains("order/create")) {
            create(req);
            resp.sendRedirect("/TestMakakus/order/list");
        }
    }

    public void create(HttpServletRequest req) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setOrderName(req.getParameter("name"));
        order.setAmount(Integer.parseInt(req.getParameter("amount")));
        order.setPrice(new BigDecimal(req.getParameter("price")));
        orderService.createOrder(order);
    }

    public void getallorders(HttpServletRequest req) {
        List<Order> orderList = orderService.getAll();
        req.setAttribute("orderList", orderList);
    }
}
