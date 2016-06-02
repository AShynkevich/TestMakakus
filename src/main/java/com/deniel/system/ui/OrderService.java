package com.deniel.system.ui;

import com.deniel.system.domain.Order;
import com.deniel.system.repository.IOrderRepository;
import com.deniel.system.repository.OrderRepository;
import com.deniel.system.repository.sql.OrderRepositorySql;
import com.deniel.system.util.InputUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by Deniel on 03.03.2016.
 */
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private Order order = new Order();
    private IOrderRepository orderRepository = new OrderRepositorySql();

    public OrderService() {
    }

    public void inputOrder(boolean isUpdate) {
        if (!isUpdate) {
            LOGGER.info("Setting random ID.");
            order.setId(UUID.randomUUID().toString());
        }

        String name = InputUtils.getValidString("Input order name: ", isUpdate);
        LOGGER.info("Setting order name.");
        order.setOrderName(name);

        Integer value = InputUtils.getInteger("Input amount [example: 50; 2]: ", isUpdate);
        LOGGER.info("Setting order amount.");
        order.setAmount(value);

        BigDecimal validString = InputUtils.getBigDecimal("Input price [example: 30; 20.5]: ", isUpdate);
        LOGGER.info("Setting order price.");
        order.setPrice(validString);
    }

    public void createOrder() {
        inputOrder(false);
        LOGGER.info("Creating order.");
        orderRepository.create(order);
    }

    public void updateOrder(Order selectedOrder) {
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
        LOGGER.info("Updating order.");
        orderRepository.update(selectedOrder);
    }

    public List<Order> getAll() {
        LOGGER.info("Read all orders.");
        return orderRepository.readAll();
    }

    public Order findById(String id) {
        LOGGER.info("Finding order by ID.");
        if (orderRepository.read(id) != null) {
            return orderRepository.read(id);
        } else {
            return new Order();
        }
    }

    public boolean deleteById(String id) {
        LOGGER.info("Deleting order");
        return orderRepository.delete(id);
    }
}

