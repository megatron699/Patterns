package lab4.lab42;

import lab1.lab12.TransportUtils;
import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.interfaces.Transport;
import lab1.lab12.models.Car;
import lab4.lab42.models.SerializableDao;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Lab42Main {
    public static void main(String[] args) throws Exception {
        // Текстовый ДАО
        System.out.println("Текстовый Дао-------------------------------------------------------------------------------------");
        testDao();
        // Сериализация
        System.out.println("Сериализируемый Дао-------------------------------------------------------------------------------------");
        TransportUtils.setDaoFactory(new SerializableDao());
        testDao();
    }

    private static void testDao() throws Exception {
        IntStream.rangeClosed(0, 2)
                .forEach(index -> {
                    try {
                        TransportUtils.save(
                                new Car(
                                        String.format("Brand %s", index), index + 1
                                )
                        );
                    } catch (DuplicateModelNameException | IOException e) {
                        e.printStackTrace();
                    }
                });

        List<Transport> transportList = TransportUtils.getAll();
        printVehicles(transportList);

        System.out.println("---------------------Удаляем бренд 2");
        TransportUtils.removeByBrandName("Brand 2");
        printVehicles(TransportUtils.getAll());

        System.out.println("---------------------Печатаем бренд 1");
        System.out.println(
                TransportUtils.toColumnString(
                        TransportUtils.getByBrandName("Brand 1")
                )
        );
    }

    private static void printVehicles(List<Transport> vehicles) {
        vehicles.forEach(el -> {
            System.out.println(TransportUtils.toColumnString(el));
        });
    }

}
