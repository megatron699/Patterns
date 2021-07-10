package lab3;

import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.interfaces.Transport;
import lab1.lab12.models.Car;
import lab1.lab12.models.Motorcycle;
import lab2.lab21.InputStreamAdapter;
import lab3.lab31.models.PrintColumnHandler;
import lab3.lab31.models.PrintRowHandler;
import lab3.lab32.interfaces.Command;
import lab3.lab32.models.PrintColumnCommand;
import lab3.lab32.models.PrintRowCommand;
import lab3.lab38.models.PrintVisitor;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;

public class Lab3Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Chains Of Responsibility");

        PrintColumnHandler printColumnHandler = new PrintColumnHandler();
        PrintRowHandler printRowHandler = new PrintRowHandler();
        printColumnHandler.setNext(printRowHandler);

        Car car = new Car("Car", 3);
        Motorcycle motorcycle = new Motorcycle("Motorcycle", 4);
        printColumnHandler.handle(car, "carOutput.txt");
        printColumnHandler.handle(motorcycle, "motorcycleOutput.txt");

        System.out.println("Command");

        PrintColumnCommand printColumnCommandPrint = new PrintColumnCommand();
        PrintRowCommand printRowCommandPrint = new PrintRowCommand();
        String rowStringFileName = "rowCommand.txt";
        String columnStringFileName = "columnCommand.txt";

        System.out.println("Column Print Command");
        commandPrintMethod(car, printColumnCommandPrint, rowStringFileName);

        System.out.println("Row Print Command");
        commandPrintMethod(car, printRowCommandPrint, columnStringFileName);

        System.out.println("Iterator");

        Iterator iterator = car.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }

        System.out.println("Memento");

        Car.Memento memento = car.createMemento();

        System.out.println("Изменяем");
        car.setCarMake("New Model");
        car.setModelNameByModelName("Model №: 2", "Memento");
        carIterator(car);

        System.out.println("Получили момент обратно");
        car.setMemento(memento);
        carIterator(car);

        System.out.println("Visitor");

        Arrays.asList(new Transport[]{
                new Car("car", 3),
                new Motorcycle("MotorCycle", 3)
        }).forEach(el -> el.accept(new PrintVisitor()));

    }

    private static void carIterator(Car car) {
        System.out.printf("Model: %s%n", car.getCarMake());
        ((Iterator) car.iterator())
                .forEachRemaining(System.out::println);
    }


    private static void commandPrintMethod(Car car, Command printColumnCommandPrint, String rowStringFileName) throws Exception {
        try (Writer outputStream = new FileWriter(rowStringFileName)) {
            car.setPrintCommand(printColumnCommandPrint);
            car.print(outputStream);
        }

        try (InputStreamAdapter inputStreamAdapter = new InputStreamAdapter(new FileInputStream(rowStringFileName))) {
            Arrays.stream(inputStreamAdapter.readString())
                    .forEach(System.out::println);
        }
    }
}
