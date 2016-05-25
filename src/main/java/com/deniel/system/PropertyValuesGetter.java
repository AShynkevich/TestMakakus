package com.deniel.system;

import java.io.*;
import java.util.Properties;

/**
 * Created by Deniel on 25.05.2016.
 */
public class PropertyValuesGetter {
    private String dbURL = "";
    private String dbDriverName = "";
    private String dbName = "";
    private String dbUserName = "";
    private String dbPass = "";

    public PropertyValuesGetter() {
        getPropertyValues();
    }

    private void getPropertyValues() {
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream("tm.properties");
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException(("property file not found in the classpath"));
            }
            dbURL = properties.getProperty("dbConnectionURL");
            dbDriverName = properties.getProperty("dbDriverName");
            dbName = properties.getProperty("dbName");
            dbUserName = properties.getProperty("dbUserName");
            dbPass = properties.getProperty("dbPass");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbURL() {
        return dbURL;
    }

    public String getDbDriverName() {
        return dbDriverName;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbPass() {
        return dbPass;
    }
}


