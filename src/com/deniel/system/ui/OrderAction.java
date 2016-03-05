package com.deniel.system.ui;

import com.deniel.system.domain.Order;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Created by Deniel on 03.03.2016.
 */
public class OrderAction {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Order order = new Order();

    public void inputOrder () throws IOException {

        System.out.println("Set id");
        order.setId(reader.readLine());

        System.out.println("Set order name");
        order.setOrderName(reader.readLine());

        System.out.println("Set amount");

        boolean trigger = true;
        while (trigger) {
            try {
                order.setAmount(Integer.parseInt(reader.readLine()));
                trigger = false;
                break;
            } catch (NumberFormatException e) {
                System.out.println("This key is not allowed here. Try again.");
            }
        }

        System.out.println("Set price");

        boolean trigger2 = true;
        while (trigger2) {
            try {
                String price = reader.readLine();
                BigDecimal bigDecimal = new BigDecimal(price.replaceAll(",", ""));
                order.setPrice(bigDecimal);
                trigger2 = false;
                break;
            } catch (NumberFormatException e) {
                System.out.println("This key is not allowed here.");
            }
        }


    }

    public void outputOrder() {
        System.out.println("Your order is:");
        System.out.println("ID: " + order.getId());
        System.out.println("Name: " + order.getOrderName());
        System.out.println("Amount: " + order.getAmount());
        System.out.println("Price" + order.getPrice());
    }


}
