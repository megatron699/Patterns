package lab1.lab12;

import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.exceptions.ModelPriceOutOfBoundsException;
import lab1.lab12.exceptions.NoSuchModelNameException;
import lab1.lab12.interfaces.Transport;
import lab1.lab12.interfaces.TransportFactory;
import lab1.lab12.models.Car;
import lab1.lab12.models.CarFactory;
import lab1.lab12.models.Motorcycle;
import lab1.lab12.models.MotorcycleFactory;

public class Lab12Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException {
        System.out.println("Car");
        Transport car = new Car("BMW", 3);
        printDefaultTask(car);

        System.out.println("\nMotorcycle");
        Transport motorcycle = new Motorcycle("Yamaha", 3);
        printDefaultTask(motorcycle);

        System.out.println("Average price");

        System.out.println("Car average price");
        printAveragePrice(car);

        System.out.println("Motorcycle average price");
        printAveragePrice(motorcycle);

        System.out.println("Factory method");
        MotorcycleFactory motorcycleFactory = new MotorcycleFactory();
        CarFactory carFactory = new CarFactory();

        System.out.println("Car Factory method");
        Car carInstance = (Car) factoryMethodTask(carFactory);

        System.out.println("Motorcycle Factory method");
        Motorcycle motorCycleInstance = (Motorcycle) factoryMethodTask(motorcycleFactory);

        System.out.println("Prototype");
        System.out.println("Car Prototype");
        prototypeTask(carInstance);

        System.out.println("Motorcycle prototype");
        prototypeTask(motorCycleInstance);

    }

    public static void printDefaultTask(Transport transport) throws DuplicateModelNameException, NoSuchModelNameException {
        System.out.println("Get car make");
        System.out.printf("Car make: %s\n", transport.getCarMake());

        System.out.println("Add new car make");
        transport.setCarMake("Audi");
        System.out.printf("Car make: %s\n", transport.getCarMake());

        System.out.println("Print all models");
        TransportUtils.printNames(transport);

        System.out.println("Add new name for model");
        String newModelName = "R8";
        transport.setModelNameByModelName("Model №: 1", newModelName);
        TransportUtils.printNames(transport);

        System.out.println("Add new price for model");
        System.out.printf("Model price %s: %s\n", newModelName, transport.getPriceByModelName(newModelName));
        transport.setPriceByModelName(newModelName, 25000);
        System.out.printf("Model price %s: %s\n", newModelName, transport.getPriceByModelName(newModelName));

        System.out.println("Print all prices");
        TransportUtils.printPrices(transport);

        System.out.println("Add new model");
        String addModelName = "A3";
        double addModelPrice = 30000;
        transport.addNewModel(addModelName, addModelPrice);
        TransportUtils.printNames(transport);

        System.out.println("Delete added model");
        transport.removeModelByModelName(addModelName);
        TransportUtils.printNames(transport);

        System.out.println("NoSuchModelNameException");
        try {
            transport.getPriceByModelName("test");
        } catch (NoSuchModelNameException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("DuplicateModelNameException");
        try {
            transport.setModelNameByModelName(newModelName, newModelName);
        } catch (DuplicateModelNameException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("ModelPriceOutOfBoundsException");
        try {
            transport.setPriceByModelName(newModelName, -1);
        } catch (ModelPriceOutOfBoundsException exception) {
            exception.printStackTrace(System.out);
        }
    }

    public static void printAveragePrice(Transport transport) {
        System.out.println("Print all models");
        TransportUtils.printNames(transport);

        System.out.println("Print all prices");
        TransportUtils.printPrices(transport);

        System.out.println("Average price");
        System.out.printf("Average price: %s\n", TransportUtils.getAveragePrice(transport));
    }

    public static Transport factoryMethodTask(TransportFactory transportFactory) throws DuplicateModelNameException {
        TransportUtils.setFactory(transportFactory);

        Transport transport = TransportUtils.createInstance("Toyota", 5);
        TransportUtils.printNames(transport);
        TransportUtils.printPrices(transport);

        return transport;
    }

    public static void prototypeTask(Transport transport) throws CloneNotSupportedException, DuplicateModelNameException, NoSuchModelNameException {
        Transport clone = transport.clone();
        clone.setCarMake("Clone of car make");
        clone.setModelNameByModelName("Model №: 3", "Model clone");
        clone.removeModelByModelName("Model №: 4");

        System.out.println("Original object");
        TransportUtils.printNames(transport);

        System.out.println("Cloned object with different car make and the name of one of the models");
        TransportUtils.printNames(clone);
    }
}
