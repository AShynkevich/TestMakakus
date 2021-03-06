package com.deniel.system.ui;

import com.deniel.system.TmSystemException;
import com.deniel.system.domain.Order;
import com.deniel.system.util.InputUtils;
import com.deniel.system.util.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Logic {
    private static final Logger LOGGER = LoggerFactory.getLogger(Menu.class);

    private OrderService orderService = new OrderService();
    private Menu menu = new Menu();

    public Logic() {
    }

    public void logic() {
        int key = -1;
        try {
            while (key != 0) {
                menu.menu();
                key = menu.pressKey();
                switch (key) {
                    case 1:
                        makeOrder();
                        break;
                    case 2:
                        loadOrders();
                        break;
                    case 3:
                        loadOneOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        System.out.println("Not implemented yet");
                        break;
                    case 6:
                        updateOrder();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Just choose number from list");
                }

                System.out.println("Press any key to continue...");
                InputUtils.readLine();
            }
        } catch (Exception e) {
            LOGGER.error("IO error.", e);
            throw new TmSystemException("IO error.", e);
        }

        for (int i = 0; i < 25; ++i) {
            System.out.println();
        }
    }

    private void makeOrder() {
        System.out.println("Make order");
        orderService.createOrder(inputOrder(false));
    }

    private void loadOrders() {
        List<Order> orders = orderService.getAll();
        if (orders.size() == 0) {
            System.out.println("There is no orders!");
        } else {
            System.out.println("All orders:\n" + orders);
        }
    }

    private void loadOneOrder() {
        Order order = orderService.findById(menu.inputOrderId());

        if (order.getId() == null) {
            System.out.println("Order not found!");
        } else {
            System.out.println(order);
        }
    }

    private void removeOrder() {
        List<Order> orders = orderService.getAll();
        if (orders.size() == 0) {
            System.out.println("There is no orders!");
        } else {
            System.out.println(orders);

            if (orderService.deleteById(menu.inputOrderId())) {
                System.out.println("Order removed!");
            } else {
                System.out.println("Order not found!");
            }
        }
    }

    private void updateOrder() {
        List<Order> orders = orderService.getAll();
        if (orders.size() == 0) {
            System.out.println("There is no orders!");
        } else {
            System.out.println(orders);
            String id = menu.inputOrderId();
            Order order = inputOrder(true);
            order.setId(id);

            if (orderService.findById(id) != null) {
                orderService.updateOrder(order);
                System.out.println("Order updated!");
            } else {
                LOGGER.error("Order not found!");
                System.out.println("Order not found!");
            }
        }
    }

    public Order inputOrder(boolean isUpdate) {
        Order order = new Order();

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

        return order;
    }
}

