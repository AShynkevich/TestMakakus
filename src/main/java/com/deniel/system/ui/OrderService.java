package com.deniel.system.ui;

import com.deniel.system.domain.Order;
import com.deniel.system.repository.IOrderRepository;
import com.deniel.system.repository.sql.OrderRepositorySql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Deniel on 03.03.2016.
 */
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private IOrderRepository orderRepository = new OrderRepositorySql();

    public OrderService() {
    }

    public void createOrder(Order order) {
        LOGGER.info("Creating order.");
        orderRepository.create(order);
    }

    public void updateOrder(Order selectedOrder) {
        Order order = findById(selectedOrder.getId());

        if (selectedOrder.getOrderName() != null) {
            order.setOrderName(selectedOrder.getOrderName());
        }
        if (selectedOrder.getAmount() != null) {
            order.setAmount(selectedOrder.getAmount());
        }
        if (selectedOrder.getPrice() != null) {
            order.setPrice(selectedOrder.getPrice());
        }
        LOGGER.info("Updating order.");
        orderRepository.update(order);
    }

    public List<Order> getAll() {
        LOGGER.info("Read all orders.");
        return orderRepository.readAll();
    }

    public Order findById(String id) {
        LOGGER.info("Finding order by ID.");
        return orderRepository.read(id);
    }

    public boolean deleteById(String id) {
        LOGGER.info("Deleting order");
        return orderRepository.delete(id);
    }
}

