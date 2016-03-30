package com.deniel.system.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;


import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/**
 * Created by alexshaman on 3/7/16.
 */
public class Validation {
    private Validation() {
    }

    public static final Logger logger = Logger.getLogger(Validation.class);

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
                logger.debug ("This key is not allowed here");
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }
}
