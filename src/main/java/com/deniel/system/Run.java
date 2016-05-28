package com.deniel.system;

import com.deniel.system.ui.Logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Deniel on 28.02.2016.
 */
public class Run {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try {
            new Logic().logic();
        } catch (FileNotFoundException e) {
            System.out.println("File tm.properties not found!");
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

