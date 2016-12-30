package com.knalx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by knalx on 10.04.16.
 */
public class ConfigReader {

    Logger log = LoggerFactory.getLogger(this.getClass());

    public String getProperty(String propName) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {

            Properties prop = new Properties();
            if (inputStream != null) {
                prop.load(inputStream);
            }
            String property = prop.getProperty(propName);
            log.info("prop: " + propName + " - "+ property);
            return property;
        }
    }

    public InputStream getCatPhotoStream() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cats.png");
        return inputStream;
    }
}