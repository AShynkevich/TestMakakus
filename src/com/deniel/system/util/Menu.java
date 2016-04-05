package com.deniel.system.util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Menu {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void menu() {
        System.out.println("1) Make Order");
        System.out.println("2) Load All Orders");
        System.out.println("3) Load Order by ID");
        System.out.println("3) Get Order");
        System.out.println("4) Exit");
        System.out.print("Make a choice: ");
    }

    public int pressKey() throws IOException {
        int key = 0;
        String s = reader.readLine();
        try {
            key = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("That key is not allowed here.");
        }
        return key;
    }

    public String getOrderID() {
        String ID = null;
        try {
            ID = reader.readLine();
            return ID;
        } catch (IOException e) {
            System.out.println(e);
        }
        return ID;
    }
}

