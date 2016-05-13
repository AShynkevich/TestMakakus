package com.deniel.system.ui;

import com.deniel.system.domain.Order;
import com.deniel.system.repository.IOrderRepository;
import com.deniel.system.repository.OrderRepository;
import com.deniel.system.util.InputUtils;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Deniel on 03.03.2016.
 */
public class OrderService {

    private Order order = new Order();
    private IOrderRepository orderRepository = new OrderRepository();

    public void inputOrder(boolean isUpdate) throws IOException {
        if (!isUpdate) {
            order.setId(InputUtils.getValidString("Input order Id: ", isUpdate));
        }

        String name = InputUtils.getValidString("Input order name: ", isUpdate);
        order.setOrderName(name);

        Integer value = InputUtils.getInteger("Input amount [example: 50; 2]: ", isUpdate);
        order.setAmount(value);

        BigDecimal validString = InputUtils.getBigDecimal("Input price [example: 30; 20.5]: ", isUpdate);
        order.setPrice(validString);
    }

    public void createOrder() throws IOException {
        inputOrder(false);
        orderRepository.create(order);
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

        orderRepository.update(selectedOrder);
    }
}

