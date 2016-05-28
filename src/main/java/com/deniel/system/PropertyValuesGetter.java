package com.deniel.system;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Properties;

/**
 * Created by Deniel on 25.05.2016.
 */
public class PropertyValuesGetter {
    private String dbURL = "";
    private String dbName = "";
    private String dbUserName = "";
    private String dbPass = "";
    private String DEFAULT_TMPROPERTIES_PATH = "/tm.properties";

    public PropertyValuesGetter() throws IOException {
        getPropertyValues();
    }

    private void getPropertyValues() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream;
        String categoryPath = System.getProperty("properties.path");
        if (StringUtils.isBlank(categoryPath)) {
            inputStream = getClass().getResourceAsStream(DEFAULT_TMPROPERTIES_PATH);
        } else {
            File file = new File(categoryPath + DEFAULT_TMPROPERTIES_PATH);
            inputStream = new FileInputStream(file);
        }
        properties.load(inputStream);
        dbURL = properties.getProperty("dbConnectionURL");
        dbName = properties.getProperty("dbName");
        dbUserName = properties.getProperty("dbUserName");
        dbPass = properties.getProperty("dbPass");
        inputStream.close();
    }


    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbURL() {
        return dbURL;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbPass() {
        return dbPass;
    }
}


