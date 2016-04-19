package com.deniel.system.ui;

import com.deniel.system.domain.Order;
import com.deniel.system.util.OrderWriterReader;
import com.deniel.system.util.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Created by Deniel on 03.03.2016.
 */
public class OrderAction {
    public static final String STRING = "\\w+";
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Order order = new Order();
    OrderWriterReader orderWriterReader = new OrderWriterReader();

    public void inputOrder(boolean isUpdate) throws IOException {

        if (isUpdate == false) {
            System.out.println("Input Id: ");
            order.setId(reader.readLine());
        }

        String name = Validation.getValidString("Input order name: ", STRING, isUpdate);
        order.setOrderName(name);

        Integer value = Validation.getInteger("Input amount [example: 50; 2]: ", isUpdate);
        order.setAmount(value);

        BigDecimal validString = Validation.getBigDecimal("Input price [example: 30; 20.5]: ", isUpdate);
        order.setPrice(validString);
    }

    public void createOrder() throws IOException {
        inputOrder(false);
        orderWriterReader.create(order);
    }

    public void updateOrder(Order selectedOrder) throws IOException {

        inputOrder(true);

        if (order.getOrderName() != null) {
            selectedOrder.setOrderName(order.getOrderName());
        }

        if (order.getAmount() != null) {
            selectedOrder.setAmount(order.getAmount());
        }

        if (order.getPrice() != null) {
            selectedOrder.setPrice(order.getPrice());
        }

        orderWriterReader.update(selectedOrder);
    }
}

