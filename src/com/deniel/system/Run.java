package com.deniel.system;

import com.deniel.system.repository.OrderRepository;
import com.deniel.system.ui.Logic;
import com.deniel.system.ui.OrderService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Deniel on 28.02.2016.
 */
public class Run {
    public static void main(String[] args) throws IOException {
        new Logic().logic();
    }
}

