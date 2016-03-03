package com.deniel.system.domain;

import com.deniel.system.util.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Created by Deniel on 03.03.2016.
 */
public class OrderAction {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Order order = new Order();

    public void set () throws IOException {

        System.out.println("Set id");
        order.setId(reader.readLine());

        System.out.println("Set order name");
        order.setOrderName(reader.readLine());

        System.out.println("Set amount");
        order.setAmount(Integer.parseInt(reader.readLine()));

        System.out.println("Set price");
        String price = reader.readLine();

        BigDecimal bigDecimal = new BigDecimal(price.replaceAll(",", ""));
        order.setPrice(bigDecimal);
    }

    public void get() {
        System.out.println("Your order is:");
        System.out.println("ID: " + order.getId());
        System.out.println("Name: " + order.getOrderName());
        System.out.println("Amount: " + order.getAmount());
        System.out.println("Price" + order.getPrice());
    }
}
