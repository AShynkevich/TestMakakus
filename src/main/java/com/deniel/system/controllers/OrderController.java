package com.deniel.system.controllers;

import com.deniel.system.domain.Order;
import com.deniel.system.ui.OrderService;
import com.deniel.system.ui.validation.AbstractValidity;
import com.deniel.system.ui.validation.StringEmptyValidator;

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
public class OrderController extends AbstractValidity {
    public static final String NAME_PARAMETER = "name";
    public static final String AMOUNT_PARAMETER = "amount";
    public static final String PRICE_PARAMETER = "price";
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
    private static final String UPDATE_FORM = ORDER_PATH + "/updateform";
    private static final String UPDATE_ORDER_ACTION = ORDER_PATH + "/update";
    private static final String NOT_FOUND = ORDER_PATH + "/notfound";
    private static final String ERROR = ORDER_PATH + "/error";

    private OrderService orderService = new OrderService();
    private ControllerValidation controllerValidation = new ControllerValidation();

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
        } else if (UPDATE_FORM.equals(address)) {
            getOrderUpdateForm(req, resp, address);
        }
    }

    private void doPost(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        if (address.contains(CREATE_ORDER_ACTION)) {
            createOrder(req, resp);
        } else if (SEARCH_ORDER_ACTION.equals(address)) {
            searchOrder(req, resp);
        } else if (DELETE_ORDER_ACTION.equals(address)) {
            deleteOrder(req, resp);
        } else if (UPDATE_ORDER_ACTION.equals(address)) {
            updateOrder(req, resp);
        }
    }

    private void getOrdersList(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        injectAllOrders(req);
        performForward(address, req, resp);
    }

    private void getOrderCreateForm(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        performForward(address, req, resp);
    }

    private void getOrderSearchForm(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        injectAllOrders(req);
        performForward(address, req, resp);
    }

    private void getOrderDeleteForm(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        injectAllOrders(req);
        performForward(address, req, resp);
    }

    private void getOrderUpdateForm(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        injectAllOrders(req);
        performForward(address, req, resp);
    }

    private void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_PARAMETER);
        String amount = req.getParameter(AMOUNT_PARAMETER);
        String price = req.getParameter(PRICE_PARAMETER);
        List<String> errors = validate(new StringEmptyValidator(NAME_PARAMETER, name),
                                       new StringEmptyValidator(AMOUNT_PARAMETER, amount),
                                       new StringEmptyValidator(PRICE_PARAMETER, price));
        if (!errors.isEmpty()) {
            req.setAttribute("messages", errors);
            performForward(ERROR, req, resp);
            return;
        }
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setOrderName(name);
        order.setAmount(Integer.parseInt(amount));
        order.setPrice(new BigDecimal(price));
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

    private void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String amount = req.getParameter("amount");
        String price = req.getParameter("price");
        Order order = findById(req);
        String validationResult = controllerValidation.validUpdating(amount, price);
        if (order != null) {
            order.setOrderName(name);
            if (validationResult.equals("OK")) {
                order.setAmount(Integer.parseInt(amount));
                order.setPrice(new BigDecimal(price));
                orderService.updateOrder(order);
                performRedirect(req.getContextPath() + LIST_ORDERS, resp);
            } else
                req.setAttribute("message", validationResult);
            performForward(ERROR, req, resp);
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

    private void performForward(String address, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MessageFormat.format(WEBINF_FMT, address)).forward(req, resp);
    }

    private void performRedirect(String address, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(address);
    }
}
