package com.deniel.system.ui.validation;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/**
 * Created by alexander on 22.7.16.
 */
public class StringEmptyValidator implements IValidator {
    private String parameterName;
    private String value;

    public StringEmptyValidator(String parameterName, String value) {
        this.parameterName = parameterName;
        this.value = value;
    }

    @Override
    public boolean validate() {
        return StringUtils.isNotBlank(value);
    }

    @Override
    public String getErrorMessage() {
        return MessageFormat.format("Parameter \"{0}\" should not be empty", parameterName);
    }
}
