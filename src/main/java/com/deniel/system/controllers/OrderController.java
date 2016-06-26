package com.deniel.system.controllers;

import com.deniel.system.domain.Order;
import com.deniel.system.ui.OrderService;

import javax.servlet.ServletException;
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
public class OrderController {
    private OrderService orderService = new OrderService();
    private static final String WEBINF_FMT = "/WEB-INF/jsp/{0}.jsp";

    public void performRequest(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        if (req.getMethod().equals("POST")) {
            doPost(req, resp, address);
        } else {
            doGet(req, resp, address);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        if (address.equals("/order/createform")) {
            req.getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/createform")).forward(req, resp);
        } else if (address.equals("/order/list")) {
            getallorders(req);
            req.getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/list")).forward(req, resp);
        } else if (address.equals("/order/searchform")) {
            getallorders(req);
            req.getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/searchform")).forward(req, resp);
        } else if (address.equals("/order/deleteform")) {
            getallorders(req);
            req.getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/deleteform")).forward(req, resp);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        if (address.contains("order/create")) {
            create(req);
            resp.sendRedirect("/TestMakakus/order/list");
        } else if (address.equals("/order/search")) {
            Order order = findById(req);
            if (order != null) {
                req.setAttribute("order", order);
                req.getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/searchresult")).forward(req, resp);
            } else {
                req.getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/notfound")).forward(req, resp);
            }
        } else if (address.equals("/order/delete")) {
            boolean deleted = deleteById(req);
            if (deleted) {
                getallorders(req);
                req.getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/deleteform")).forward(req, resp);
            } else {
                req.getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/notfound")).forward(req, resp);
            }
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

    public Order findById(HttpServletRequest req) {
        return orderService.findById(req.getParameter("Id"));
    }

    public boolean deleteById(HttpServletRequest req) {
        return orderService.deleteById(req.getParameter("Id"));
    }
}
