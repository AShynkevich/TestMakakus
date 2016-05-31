package com.deniel.system.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Menu {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void menu() {
        System.out.println("1) Make Order");
        System.out.println("2) Load All Orders");
        System.out.println("3) Load Order by ID");
        System.out.println("4) Remove Order by ID");
        System.out.println("5) Get Order");
        System.out.println("6) Update order by ID");
        System.out.println("0) Exit");
        System.out.print("Make a choice: ");
    }

    public int pressKey() throws IOException {
        int key = -2;
        String s = reader.readLine();
        try {
            key = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("That key is not allowed here.");
        }
        return key;
    }

    public String getOrderId() {
        String ID = null;
        try {
            ID = reader.readLine();
            return ID;
        } catch (Exception e) {
            System.out.println(e);
        }
        return ID;
    }
}

