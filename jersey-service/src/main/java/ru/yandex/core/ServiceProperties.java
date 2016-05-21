package ru.yandex.core;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Admin on 30.04.2016.
 */
public class ServiceProperties {

    private final static Logger logger = Logger.getLogger(ServiceProperties.class);
    private static String BASE_URI;
    private FileInputStream fileInputStream;

    public String getProperties() throws IOException {
        try {
            Properties properties = new Properties();
            String propFileName = "config.properties";
            fileInputStream = new FileInputStream(propFileName);

            if (fileInputStream != null) {
                properties.load(fileInputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            // get the property value and print it out
            String protocol = properties.getProperty("protocol");
            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            String path = properties.getProperty("path");

            BASE_URI = protocol + host + ":" + port + "/" + path + "/";
            logger.info(BASE_URI);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            logger.info("Exception: " + e.getMessage());
        } finally {
            fileInputStream.close();
        }
        return BASE_URI;
    }
}
