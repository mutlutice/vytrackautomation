package com.vytrack.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties configFile;

    static {


        String path = "configuration.properties";
        try {
            FileInputStream input = new FileInputStream(path);
            configFile = new Properties();
            configFile.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String getProperty(String key) {
        return configFile.getProperty(key);
    }
}
