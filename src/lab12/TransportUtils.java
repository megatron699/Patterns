package lab12;

import lab12.exceptions.DuplicateModelNameException;
import lab12.interfaces.Transport;
import lab12.interfaces.TransportFactory;
import lab12.models.CarFactory;

public class TransportUtils {
    private static TransportFactory factory = new CarFactory();

    public static void setFactory(/*@NotNull*/ TransportFactory newFactory) {
        factory = newFactory;
    }

    public static void initialization(Transport transport, int countElement) throws DuplicateModelNameException {
        for (int i = 0; i < countElement; i++) {
            transport.addNewModel(String.format("Модель номер: %d", i + 1), (i + 1) * 1000);
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
        System.out.printf("Список моделей в марке: %s%n", transport.getModel());
        for (String name : transport.getModelsNames()) {
            System.out.println(name);
        }
    }

    public static void printPrices(Transport transport) {
        System.out.printf("Список цен в марке: %s%n", transport.getModel());
        for (double price : transport.getModelsPrices()) {
            System.out.println(price);
        }

    }

    public static Transport createInstance(String modelName, int countModel) throws DuplicateModelNameException {
        return factory.createInstance(modelName, countModel);
    }

}
