package com.deniel.system.ui.validation;

/**
 * Created by aliaksandr on 7/3/16.
 */
public interface IValidator {

    boolean validate();

    String getErrorMessage();
}
