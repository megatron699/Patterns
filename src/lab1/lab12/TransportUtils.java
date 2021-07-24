package lab1.lab12;

import com.sun.istack.internal.NotNull;
import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.interfaces.Transport;
import lab1.lab12.interfaces.TransportFactory;
import lab1.lab12.models.Car;
import lab1.lab12.models.CarFactory;
import lab2.lab22.TransportSynchronizedDecorator;
import lab4.lab42.interfaces.DaoFactory;
import lab4.lab42.models.TextDao;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class TransportUtils {
    private static TransportFactory factory = new CarFactory();
    private static DaoFactory daoFactory = new TextDao();

    public static void setFactory(TransportFactory newFactory) {
        factory = newFactory;
    }

    public static void setDaoFactory(@NotNull DaoFactory newDaoFactory) {
        daoFactory = newDaoFactory;
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
        System.out.printf("List of model in car make: %s\n", transport.getCarMake());
        System.out.printf("Instance of %s\n", transport.getClass().getSimpleName());
        for (String name : transport.getModelsNames()) {
            System.out.println(name);
        }
    }

    public static void printPrices(Transport transport) {
        System.out.printf("List of price in car make: %s\n", transport.getCarMake());
        System.out.printf("Instance of %s\n", transport.getClass().getSimpleName());
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

    public static String toRowString(Transport transport) {
        return getStringTransportWithSeparator(transport, ";");
    }

    public static String toColumnString(Transport vehicle) {
        return getStringTransportWithSeparator(vehicle, "%n");
    }

    private static String getStringTransportWithSeparator(Transport transport, String rowSeparator) {
        StringBuilder stringBuilder = new StringBuilder(
                String.format("Марка: %s" + rowSeparator + "Количество моделей: %s" + rowSeparator, transport.getCarMake(),
                        transport.getSize()));

        String[] models = transport.getModelsNames();
        double[] prices = transport.getModelsPrices();
        for (int i = 0; i < models.length; i++) {
            stringBuilder.append(
                    String.format("Модель: %s, Цена: %s" + rowSeparator, models[i], prices[i])
            );
        }

        return stringBuilder.toString();
    }

    public static Transport getByBrandName(String brandName) throws Exception {
        return daoFactory.getByBrandName(brandName);
    }

    public static void removeByBrandName(String brandName) throws Exception {
        daoFactory.remove(brandName);
    }

    public static void save(Transport transport) throws IOException {
        daoFactory.add(transport);
    }

    public static List<Transport> getAll() throws IOException {
        return daoFactory.getAll();
    }

    public static String modelListToString(Transport transport) {
        String[] modelNames = transport.getModelsNames();
        double[] prices = transport.getModelsPrices();
        int size = transport.getSize();
        return IntStream.range(0, size)
                .mapToObj(index ->
                        String.format("brandName:%s,price:%s,", modelNames[size - 1 - index], prices[size - 1 - index])
                )
                .reduce((value, accum) -> accum += value).get();
    }

}
