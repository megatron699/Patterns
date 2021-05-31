package lab1.lab12;

import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.interfaces.Transport;
import lab1.lab12.interfaces.TransportFactory;
import lab1.lab12.models.CarFactory;
import lab2.lab22.TransportSynchronizedDecorator;

public class TransportUtils {
    private static TransportFactory factory = new CarFactory();

    public static void setFactory(/*@NotNull*/ TransportFactory newFactory) {
        factory = newFactory;
    }

    public static void initialization(Transport transport, int countElement) throws DuplicateModelNameException {
        for (int i = 0; i < countElement; i++) {
            transport.addNewModel(String.format("Model №: %d", i + 1), (i + 1) * 1000);
        }
    }

    public static double getAveragePrice(Transport transport) {
        double sum = 0;
        double[] prices = transport.getModelsPrices();
        for (double price : prices) {
            sum += price;
        }

        return sum / (double) transport.getSize();
    }

    public static void printNames(Transport transport) {
        System.out.printf("List of model in car make: %s%n", transport.getCarMake());
        for (String name : transport.getModelsNames()) {
            System.out.println(name);
        }
    }

    public static void printPrices(Transport transport) {
        System.out.printf("List of price in car make: %s%n", transport.getCarMake());
        for (double price : transport.getModelsPrices()) {
            System.out.println(price);
        }

    }

    public static Transport synchronizedTransport(Transport transport) {
        return new TransportSynchronizedDecorator(transport);
    }

    public static Transport createInstance(String modelName, int countModel) throws DuplicateModelNameException {
        return factory.createInstance(modelName, countModel);
    }

}
