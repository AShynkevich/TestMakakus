package com.deniel.system.ui;

import com.deniel.system.domain.Order;
import com.deniel.system.util.Menu;
import com.deniel.system.repository.OrderRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Logic {
    OrderRepository writerReader = new OrderRepository();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    OrderAction action = new OrderAction();

    public void logic() throws IOException {
        int key = -1;
        Menu menu = new Menu();

        while (key != 0) {
            menu.menu();
            key = menu.pressKey();

            switch (key) {
                case 1:
                    System.out.println("Make order");
                    action.createOrder();
                    break;
                case 2:
                    if (writerReader.readAll().size() == 0) {
                        System.out.println("There is no orders!");
                    } else {
                        System.out.println("All orders:\n" + writerReader.readAll());
                    }

                    break;
                case 3:
                    System.out.println("Enter order ID:");
                    Order order = writerReader.read(menu.getOrderId());

                    if (order.getId() == null) {
                        System.out.println("Order not found!");
                    } else {
                        System.out.println(order);
                    }

                    break;
                case 4:
                    if (writerReader.readAll().size() == 0) {
                        System.out.println("There is no orders!");
                    } else {
                        System.out.println(writerReader.readAll());
                        System.out.println("Enter order ID:");

                        if (writerReader.delete(menu.getOrderId())) {
                            System.out.println("Order removed!");
                        } else {
                            System.out.println("Order not found!");
                        }
                    }

                    break;
                case 5:
                    System.out.println("Not realised yet");
                    break;
                case 6:
                    if (writerReader.readAll().size() == 0) {
                        System.out.println("There is no orders!");
                    } else {
                        System.out.println(writerReader.readAll());
                        System.out.println("Enter order ID:");
                        String id = menu.getOrderId();
                        Order selectedOrder = writerReader.read(id);

                        if (selectedOrder.getId() == null) {
                            System.out.println("Order not found!");
                        } else {
                            action.updateOrder(selectedOrder);
                            System.out.println("Order updated!");
                        }
                    }

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
}

