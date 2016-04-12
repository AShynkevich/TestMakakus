package com.deniel.system.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Created by alexshaman on 3/7/16.
 */
public class Validation {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getValidString(String message, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        while (true) {
            try {
                System.out.println(message);
                String line = reader.readLine();
                if (pattern.matcher(line).matches()) {
                    return line;
                }
                System.out.println("This key is not allowed here");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private Validation() {
    }
}
