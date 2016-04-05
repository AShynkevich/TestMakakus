package com.deniel.system.ui;

import com.deniel.system.domain.Order;
import com.deniel.system.util.Menu;
import com.deniel.system.util.OrderWriterReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Logic {

    public void logic() throws IOException {
        int key = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Menu menu = new Menu();
        OrderAction action = new OrderAction();
        OrderWriterReader writerReader = new OrderWriterReader();

        while (key != 5) {
            menu.menu();

            key = menu.pressKey();

            switch (key) {
                case 1:
                    System.out.println("Make order");

                    action.inputOrder();

                    break;
                case 2:
                    System.out.println("All orders:");

                    System.out.println(writerReader.loadAllOrders());

                    break;
                case 3:
                    System.out.println("Enter order ID:");

                    String ID = menu.getOrderID();

                    Order order = writerReader.getOrderById(ID);

                    if (order.getId() == null) {
                        System.out.println("Order not found!");
                    } else {
                        System.out.println(order);
                    }

                    break;

                case 4:
                    System.out.println("Get order");
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Just choose number from 1 to 4...");
            }

            System.out.println("Press any key to continue...");

            reader.readLine();

            for (int i = 0; i < 25; ++i) {
                System.out.println();
            }
        }
    }


}

