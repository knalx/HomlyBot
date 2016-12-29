package com.knalx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by knalx on 10.04.16.
 */
public class ConfigReader {
    public String getProperty(String propName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();
            if (inputStream != null) {
                prop.load(inputStream);
            }

            // get the property value and print it out
            String welcomePath = prop.getProperty(propName);
            System.out.println(propName + " : " + welcomePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
