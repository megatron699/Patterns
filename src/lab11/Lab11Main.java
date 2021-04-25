
package lab11;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lab11Main {

    public static void main(String[] args) {
        // 1.1	Разработка приложения с использованием паттерна Singleton
        lab11();        
    }
    
    private static void lab11(){
        try {
            ConfigManager manager = ConfigManager.getInstance();
            System.out.println("Reading from config...");
            System.out.println("Value1: " + manager.getPropertyByName("MAIN_PROPERTY"));
            System.out.println("Value2: " + manager.getPropertyByName("SINGLETON_PROPERTY"));
        } catch (IOException ex) {
            Logger.getLogger(Lab11Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

