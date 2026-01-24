package utils;

import base.BaseTest;
import lombok.extern.flogger.Flogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger =
            LoggerFactory.getLogger(ConfigReader.class);

    private static Properties properties;

    static {
        {
            try {
                FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
                System.out.println("File Read Successfully");
                properties = new Properties();
                properties.load(fis);
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }




}
