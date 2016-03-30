package com.deniel.system.util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Menu {
    private static final Logger LOGGER = Logger.getLogger(Menu.class);

    public void menu() {
        System.out.println("1) Make Order");
        System.out.println("2) Load Order");
        System.out.println("3) Get Order");
        System.out.println("4) Exit");
        System.out.print("Make a choice: ");
    }

    public int input() throws IOException {
        int button = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String s = reader.readLine();

        try {
            button = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            LOGGER.warn("That key is not allowed here.");
        }
        return button;
    }
}

