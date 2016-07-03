package com.deniel.system.ui.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aliaksandr on 7/3/16.
 */
public abstract class AbstractValidity {

    protected List<String> validate(IValidator... validators) {
        List<String> messages = new ArrayList<>();
        for (IValidator validator : validators) {
            if (!validator.validate()) {
                messages.add(validator.getErrorMessage());
            }
        }
        return messages;
    }
}
