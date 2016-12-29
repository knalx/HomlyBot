package com.knalx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by knalx on 10.04.16.
 */
public class ConfigReader {
    public String getProperty(String propName) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();
            if (inputStream != null) {
                prop.load(inputStream);
            }
            return prop.getProperty(propName);
        }
    }

    public InputStream getCatPhotoStream() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cats.png");
        return inputStream;
    }
}