/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1.lab11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public final class ConfigManager {
    private static ConfigManager manager;
    private static Properties properties;

    public String getPropertyByName(String propertyName)
    {
        return properties.getProperty(propertyName);
    }

    public synchronized static ConfigManager getInstance() throws IOException {
        manager = new ConfigManager();
        return manager;
    }

    private ConfigManager() throws IOException {

        readConfigProperties();
    }

    private void readConfigProperties() throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream("resources/lab1/config.properties")) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException ex) {
            throw new FileNotFoundException(String.format("property file not found un the classpath"));
        }
    }

}
