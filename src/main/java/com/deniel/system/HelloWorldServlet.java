package com.deniel.system;

import com.deniel.system.domain.Order;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = createOrder();
        req.setAttribute("order", order);
        getServletContext().getRequestDispatcher("/hello.jsp").forward(req, resp);
    }

    public Order createOrder() {
        Order order = new Order();
        order.setId("122345");
        order.setOrderName("PalkoKopalko");
        order.setAmount(4);
        order.setPrice(BigDecimal.valueOf(100500));
        return order;
    }
}