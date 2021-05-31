
package lab1.lab11;

import java.io.IOException;

public class Lab11Main {

    public static void main(String[] args) throws IOException {
        ConfigManager manager = ConfigManager.getInstance();
        System.out.println("Reading from config...");
        System.out.println("Value 1: " + manager.getPropertyByName("VALUE_1"));
        System.out.println("Value 2: " + manager.getPropertyByName("VALUE_2"));
    }

}

