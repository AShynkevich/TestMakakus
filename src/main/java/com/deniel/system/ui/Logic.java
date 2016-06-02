package com.deniel.system.ui;

import com.deniel.system.TmSystemException;
import com.deniel.system.domain.Order;
import com.deniel.system.util.InputUtils;
import com.deniel.system.util.Menu;

import java.util.List;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Logic {
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
            }
        } catch (Exception e) {
            throw new TmSystemException("IO error.", e);
        }

        System.out.println("Press any key to continue...");
        InputUtils.readLine();


        for (int i = 0; i < 25; ++i) {
            System.out.println();
        }
    }

    private void makeOrder() {
        System.out.println("Make order");
        orderService.createOrder();
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
        System.out.println("Enter order ID:");
        Order order = orderService.findById(menu.getOrderId());

        if (order == null) {
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
            System.out.println("Enter order ID:");

            if (orderService.deleteById(menu.getOrderId())) {
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
            System.out.println("Enter order ID:");
            String id = menu.getOrderId();
            Order selectedOrder = orderService.findById(id);

            if (selectedOrder.getId() == null) {
                System.out.println("Order not found!");
            } else {
                orderService.updateOrder(selectedOrder);
                System.out.println("Order updated!");
            }
        }
    }
}

