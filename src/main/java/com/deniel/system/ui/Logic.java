package com.deniel.system.ui;

import com.deniel.system.domain.Order;
import com.deniel.system.util.Menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Logic {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private OrderService orderService;
    private Menu menu = new Menu();

    public Logic() {
        orderService = new OrderService();
    }

    public void logic() throws IOException {
        int key = -1;

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
            reader.readLine();

            for (int i = 0; i < 25; ++i) {
                System.out.println();
            }
        }
    }

    private void makeOrder() throws IOException {
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

    private void updateOrder() throws IOException {
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

