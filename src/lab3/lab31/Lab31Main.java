package lab3.lab31;

import lab1.lab12.models.Car;
import lab1.lab12.models.Motorcycle;
import lab3.lab31.models.PrintColumnHandler;
import lab3.lab31.models.PrintRowHandler;

public class Lab31Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Цепочка зависимостей");

        PrintColumnHandler printColumnHandler = new PrintColumnHandler();
        PrintRowHandler printRowHandler = new PrintRowHandler();
        printColumnHandler.setNext(printRowHandler);

        Car car = new Car("Car", 3);
        Motorcycle motorcycle = new Motorcycle("Motorcycle", 4);
        printColumnHandler.handle(car, "carOutput.txt");
        printColumnHandler.handle(motorcycle, "motorcycleOutput.txt");
    }
}
