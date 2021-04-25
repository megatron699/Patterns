/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab11;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public final class ConfigManager {
    private final static ConfigManager manager = new ConfigManager();
    private static Properties properties;

    private ConfigManager(){
        try (FileInputStream fileInputStream = new FileInputStream("resources/lab1/config.properties")) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException ex) {
            Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPropertyByName(String propertyName)
    {
        return properties.getProperty(propertyName);
    }

    public String getMainProperty()
    {
        return properties.getProperty("MAIN_PROPERTY");
    }

    public synchronized static ConfigManager getInstance() throws IOException {
        return manager;
    }

}
