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

        while (true) {
            try {
                System.out.println("Set amount");
                order.setAmount(Integer.parseInt(reader.readLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("This key is not allowed here. Try again.");
            }
        }

        while (true) {
            try {
                System.out.println("Set price");
                String price = reader.readLine();
                BigDecimal bigDecimal = new BigDecimal(price.replaceAll(",", ""));
                order.setPrice(bigDecimal);
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
