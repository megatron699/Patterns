
package lab1.lab11;

import java.io.IOException;

public class Lab11Main {

    public static void main(String[] args) throws IOException {
        ConfigManager manager = ConfigManager.getInstance();
        System.out.println("Reading from config");
        System.out.printf("Value 1: %s\n", manager.getPropertyByName("VALUE_1"));
        System.out.printf("Value 2: %s\n", manager.getPropertyByName("VALUE_2"));
    }

}

