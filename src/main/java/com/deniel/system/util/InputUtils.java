package com.deniel.system.util;

import com.deniel.system.TmSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by alexshaman on 3/7/16.
 */
public final class InputUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(InputUtils.class);

    private static final String ONLY_NUMBER_PATTERN = "\\d+";
    private static final String FLOAT_PATTERN = "\\d+(\\.\\d*)?";
    private static final String STRING_PATTERN = "\\w+";
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private InputUtils() {
    }

    public static String getValidString(String message, boolean isUpdate) {
        return getValidString(message, STRING_PATTERN, isUpdate);
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
            return new BigDecimal(value);
        }
        return null;
    }

    private static String getValidString(String message, String regexp, boolean isUpdate) {
        Pattern pattern = Pattern.compile(regexp);

        while (true) {
                System.out.println(message);
                String line = readLine();
                if (isUpdate == true && line.equals("")) {
                    return null;
                }
                if (pattern.matcher(line).matches()) {
                    return line;
                }
                System.out.println("This key is not allowed here");
        }
    }

    public static String readLine() {
        String returnString = null;
        try {
            returnString = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("IO error", e);
            throw  new TmSystemException("IO error", e);
        }
        return returnString;
    }
}
