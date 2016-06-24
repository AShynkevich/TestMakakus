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
    private OrderController orderController = new OrderController();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String adress = req.getRequestURI().substring(req.getContextPath().length());

        if (adress.equals("/")) {
            getServletContext().getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "hello")).forward(req, resp);
        } else if (adress.equals("/order/createform")) {
            getServletContext().getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/createform")).forward(req, resp);
        } else if (adress.equals("/order/list")) {
            orderController.getallorders(req);
            getServletContext().getRequestDispatcher(MessageFormat.format(WEBINF_FMT, "/order/list")).forward(req, resp);
        } else if (adress.contains("order/create")) {
            orderController.create(req);
            resp.sendRedirect("/TestMakakus/order/list");
        } else  {
            resp.sendRedirect("http://google.com");
        }
    }
}