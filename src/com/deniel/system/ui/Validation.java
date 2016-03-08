package com.deniel.system.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Deniel on 08.03.2016.
 */
public class Validation {

    private static boolean checkWith09(String validation) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(validation);
        return m.matches();
    }

    private static boolean checkWithEmpty(String validation) {
        Pattern p = Pattern.compile("");
        Matcher m = p.matcher(validation);
        return m.matches();
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String validation() throws IOException {
        String returnString = null;
        while (true) {
            String input = reader.readLine();
            if (checkWith09(input) && !(checkWithEmpty(input))) {
                returnString = input;
            }
            break;
        }
        return returnString;
    }
}



