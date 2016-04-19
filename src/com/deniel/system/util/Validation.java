package com.deniel.system.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by alexshaman on 3/7/16.
 */
public class Validation {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final String ONLY_NUMBER_PATTERN = "\\d+";
    public static final String FLOAT_PATTERN = "\\d+(\\.\\d*)?";

    public static String getValidString(String message, String regexp, boolean isUpdate) {

        Pattern pattern = Pattern.compile(regexp);

        while (true) {
            try {
                System.out.println(message);
                String line = reader.readLine();

                if (isUpdate == true && line.equals("")) {
                    return null;
                }

                if (pattern.matcher(line).matches()) {
                    return line;
                }

                System.out.println("This key is not allowed here");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static Integer getInteger(String message, boolean isUpdate) {
        String value = getValidString(message, ONLY_NUMBER_PATTERN, isUpdate);

        if (value != null) {
            return Integer.valueOf(value);
        }
        return null;
    }

    public static BigDecimal getBigDecimal(String message, boolean isUpdate) {
        String value = getValidString(message, FLOAT_PATTERN, isUpdate);

        if (value != null) {
            return BigDecimal.valueOf(Long.parseLong(value));
        }
        return null;
    }

    private Validation() {
    }
}
