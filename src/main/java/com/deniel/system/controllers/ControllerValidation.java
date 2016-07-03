package com.deniel.system.controllers;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by DenielNote on 03.07.2016.
 */
public class ControllerValidation {
    Pattern floatPattern = Pattern.compile("\\d+(\\.\\d*)?");
    Pattern numPattern = Pattern.compile("\\d+");

    public String validCreating(String name, String amount, String price) {
        if (StringUtils.isBlank(name)) {
            return "Field name can't be empty!";
        } else if (StringUtils.isBlank(amount)) {
            return "Field amount can't be empty!";
        } else if (StringUtils.isBlank(price)) {
            return "Field price can't be empty!";
        } else if (!numPattern.matcher(amount).matches()) {
            return "Wrong format in field amount! Use only numbers";
        } else if (!floatPattern.matcher(price).matches()) {
            return "Wrong format in field price! Use only numbers";
        } else {
            return "OK";
        }
    }

    public String validUpdating(String amount, String price) {
        if (!numPattern.matcher(amount).matches()) {
            return "Wrong format in field amount! Use only numbers";
        } else if (!floatPattern.matcher(price).matches()) {
            return "Wrong format in field price! Use only numbers";
        } else
            return "OK";
    }
}

