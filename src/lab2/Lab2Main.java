package lab2;

import lab1.lab12.TransportUtils;
import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.interfaces.Transport;
import lab1.lab12.models.Car;
import lab2.lab21.InputStreamAdapter;
import lab2.lab21.OutputStreamAdapter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Lab2Main {
    public static void main(String[] args) throws IOException, DuplicateModelNameException {
        System.out.println("Adapter");
        String[] strings = {"Hi", "it", "is", "an Adapter."};
        String fileName = "outputStreamAdapter";

        try (OutputStreamAdapter outputStreamAdapter = new OutputStreamAdapter(new FileOutputStream(fileName))) {
            outputStreamAdapter.write(strings);
        }

        try (InputStreamAdapter inputStreamAdapter = new InputStreamAdapter(new FileInputStream(fileName))) {
            Arrays.stream(inputStreamAdapter.readString()).forEach(System.out::println);
        }

        System.out.println("\nDecorator");

        Car carInstance = new Car("Toyota", 5);
        Transport decorator = TransportUtils.synchronizedTransport(carInstance);

        new Thread(() -> {
            System.out.println("Thread 1");
            TransportUtils.printNames(decorator);
        }).start();
        new Thread(() -> {
            System.out.println("Thread 2");
            TransportUtils.printNames(decorator);
        }).start();
    }

}
