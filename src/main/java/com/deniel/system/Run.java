package com.deniel.system;

import com.deniel.system.ui.Logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Deniel on 28.02.2016.
 */
public class Run {
    public static void main(String[] args) throws IOException {
        new Logic().logic();
    }
}
