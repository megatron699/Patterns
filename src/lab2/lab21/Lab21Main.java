package lab2.lab21;

import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.models.Car;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Lab21Main {
    public static void main(String[] args) throws DuplicateModelNameException, IOException {
        Car carInstance = new Car("Мой бренд", 5);

        System.out.println("Адаптер");
        String[] strings = {"Hi", "it", "is", "an Adapter."};
        String test = "test";

        try (OutputStreamAdapter outputStreamAdapter = new OutputStreamAdapter(new FileOutputStream(test))) {
            outputStreamAdapter.write(strings);
        }

        try (InputStreamAdapter inputStreamAdapter = new InputStreamAdapter(new FileInputStream(test))) {
            Arrays.stream(inputStreamAdapter.readString()).forEach(System.out::println);
        }

    }
}
