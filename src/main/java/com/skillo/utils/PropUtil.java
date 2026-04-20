package com.skillo.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropUtil {

    Properties prop;
    

    public PropUtil(String filepath) {

        try {

            prop = new Properties();

            FileInputStream fis = new FileInputStream(filepath);

            prop.load(fis);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public String get(String key) {

        return prop.getProperty(key);
    }
}