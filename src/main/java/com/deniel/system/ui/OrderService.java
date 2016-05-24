package com.deniel.system.ui;

import com.deniel.system.domain.Order;
import com.deniel.system.repository.IOrderRepository;
import com.deniel.system.repository.sql.OrderRepositorySql;
import com.deniel.system.util.InputUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by Deniel on 03.03.2016.
 */
public class OrderService {

    private Order order = new Order();
    private IOrderRepository orderRepository = new OrderRepositorySql();

    public void inputOrder(boolean isUpdate) throws IOException {

        if (!isUpdate) {
            order.setId(UUID.randomUUID().toString());
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

    public List<Order> getAll() {
        return orderRepository.readAll();
    }

    public Order findById(String id) {
        if(orderRepository.read(id) != null) {
            return orderRepository.read(id);
        } else {
            return new Order();
        }
    }

    public boolean deleteById(String id) {
        return orderRepository.delete(id);
    }
}

