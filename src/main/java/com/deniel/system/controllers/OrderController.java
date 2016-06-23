package com.deniel.system.controllers;

import com.deniel.system.domain.Order;
import com.deniel.system.repository.sql.OrderRepositorySql;
import com.deniel.system.ui.OrderService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by DenielNote on 23.06.2016.
 */
public class OrderController extends HttpServlet{
    private OrderRepositorySql orderRepositorySql = new OrderRepositorySql();
    private OrderService orderService = new OrderService();

    public void create (HttpServletRequest req) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setOrderName(req.getParameter("name"));
        order.setAmount(Integer.parseInt(req.getParameter("amount")));
        order.setPrice(BigDecimal.valueOf(Integer.parseInt(req.getParameter("price"))));
        orderRepositorySql.create(order);
    }

    public void getallorders (HttpServletRequest req) {
        List<Order> orderList = orderService.getAll();
        req.setAttribute("orderList", orderList);
    }
}
