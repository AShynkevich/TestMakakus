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
    private static final String ORDER_PATH = "/order";
    private static final String LIST_ORDERS = ORDER_PATH + "/list";
    private static final String CREATE_FORM = ORDER_PATH + "/createform";
    private static final String CREATE_ORDER_ACTION = ORDER_PATH + "/create";
    private static final String SEARCH_FORM = ORDER_PATH + "/searchform";
    private static final String SEARCH_ORDER_ACTION = ORDER_PATH + "/search";
    private static final String SEARCH_RESULT = ORDER_PATH + "/searchresult";
    private static final String DELETE_FORM = ORDER_PATH + "/deleteform";
    private static final String DELETE_ORDER_ACTION = ORDER_PATH + "/delete";
    private static final String NOT_FOUND = ORDER_PATH + "/notfound";

    public void performRequest(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        if (req.getMethod().equals("POST")) {
            doPost(req, resp, address);
        } else {
            doGet(req, resp, address);
        }
    }

    private void doGet(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        if (LIST_ORDERS.equals(address)) {
            getOrdersList(req, resp, address);
        } else if (CREATE_FORM.equals(address)) {
            getOrderCreateForm(req, resp, address);
        } else if (SEARCH_FORM.equals(address)) {
            getOrderSearchForm(req, resp, address);
        } else if (DELETE_FORM.equals(address)) {
            getOrderDeleteForm(req, resp, address);
        }
    }

    private void doPost(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        if (address.contains(CREATE_ORDER_ACTION)) {
            createOrder(req, resp);
        } else if (SEARCH_ORDER_ACTION.equals(address)) {
            searchOrder(req, resp);
        } else if (DELETE_ORDER_ACTION.equals(address)) {
            deleteOrder(req,resp);
        }
    }

    private void getOrdersList (HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException{
        injectAllOrders(req);
        performForward(address, req, resp);
    }

    private void getOrderCreateForm (HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        performForward(address, req, resp);
    }

    private void getOrderSearchForm (HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException{
        injectAllOrders(req);
        performForward(address, req, resp);
    }

    private void getOrderDeleteForm (HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException{
        injectAllOrders(req);
        performForward(address, req, resp);
    }

    private void createOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setOrderName(req.getParameter("name"));
        order.setAmount(Integer.parseInt(req.getParameter("amount")));
        order.setPrice(new BigDecimal(req.getParameter("price")));
        orderService.createOrder(order);
        performRedirect(req.getContextPath() + LIST_ORDERS, resp);
    }

    private void searchOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Order order = findById(req);
        if (order != null) {
            req.setAttribute("order", order);
            performForward(SEARCH_RESULT, req, resp);
        } else {
            performForward(NOT_FOUND, req, resp);
        }
    }

    private void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        boolean deleted = deleteById(req);
        if (deleted) {
            injectAllOrders(req);
            performForward(DELETE_FORM, req, resp);
        } else {
            performForward(NOT_FOUND, req, resp);
        }
    }

    private void injectAllOrders(HttpServletRequest req) {
        List<Order> orderList = orderService.getAll();
        req.setAttribute("orderList", orderList);
    }

    private Order findById(HttpServletRequest req) {
        return orderService.findById(req.getParameter("Id"));
    }

    private boolean deleteById(HttpServletRequest req) {
        return orderService.deleteById(req.getParameter("Id"));
    }

    private void performForward (String address, HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        req.getRequestDispatcher(MessageFormat.format(WEBINF_FMT, address)).forward(req, resp);
    }

    private void performRedirect (String address, HttpServletResponse resp) throws IOException{
        resp.sendRedirect(address);
    }
}
