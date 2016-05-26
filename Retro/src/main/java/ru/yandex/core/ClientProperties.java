package ru.yandex.core;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 30.04.2016.
 */
public class ClientProperties {
    private final static Logger logger = Logger.getLogger(ClientProperties.class);
    private static String BASE_URI;
    private FileInputStream fileInputStream;
    private static final String baseUrlRegExp = "^(https?:\\/\\/((localhost)|((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?)))\\:(([0-9]{1,4})|([1-5][0-9]{4})|(6[0-4][0-9]{3})|(65[0-4][0-9]{2})|(655[0-2][0-9])|(6553[0-5]))\\/.+\\/)$";
    public String getProperties() throws IOException {
        try {
            Properties properties = new Properties();
            String propFileName = "config.properties";
            fileInputStream = new FileInputStream(propFileName);
            properties.load(fileInputStream);
            properties.load(fileInputStream);
            String protocol = properties.getProperty("protocol");
            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            String path = properties.getProperty("path");
            BASE_URI = protocol + host + ":" + port + "/" + path + "/";
            Pattern pattern = Pattern.compile(baseUrlRegExp);
            Matcher matcher = pattern.matcher(BASE_URI);
            if(matcher.find()) {
                logger.debug(BASE_URI);
            } else {
                logger.error("Some problem in config.properties file");
                throw new Exception("Some problem in config.properties file");
            }
            logger.debug(BASE_URI);
        } catch (Exception e) {
            logger.error("Exception: " + e);
        } finally {
            fileInputStream.close();
        }
        return BASE_URI;
    }
}
