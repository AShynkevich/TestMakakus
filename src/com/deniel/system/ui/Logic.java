package com.deniel.system.ui;

import com.deniel.system.util.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Deniel on 26.02.2016.
 */
public class Logic {

    public void logic() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Menu menu = new Menu();

        int button = 0;

        while (button != 4) {

            menu.menu();
            button = menu.input();

            switch (button) {
                case 1:
                    System.out.println("Make order");
                    break;
                case 2:
                    System.out.println("Load order");
                    break;
                case 3:
                    System.out.println("Get order");
                    break;
                case 4:
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

