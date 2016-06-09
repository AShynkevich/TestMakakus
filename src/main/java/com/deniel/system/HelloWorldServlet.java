package com.deniel.system;

import com.deniel.system.domain.Order;
import com.deniel.system.ui.OrderService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {
    private OrderService orderService = new OrderService();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList = orderService.getAll();
        req.setAttribute("orderList", orderList);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
    }
}