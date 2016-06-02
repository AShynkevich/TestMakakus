package com.deniel.system.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Menu {
    private static final Logger LOGGER = LoggerFactory.getLogger(Menu.class);

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

    public int pressKey() {
        int key = -2;
        String inputString = InputUtils.readLine();
        try {
            key = Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            LOGGER.warn("NF exception");
            System.out.println("That key is not allowed here.");
        }
        return key;
    }

    public String OrderIdInput() {
        String ID = null;
        try {
            ID = InputUtils.readLine();
            return ID;
        } catch (Exception e) {
            LOGGER.error("IO exception", e);
            System.out.println(e);
        }
        return ID;
    }
}

