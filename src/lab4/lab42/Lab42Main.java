package lab4.lab42;

import lab1.lab12.TransportUtils;
import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.interfaces.Transport;
import lab1.lab12.models.Car;
import lab4.lab42.models.SerializableDao;
import lab4.lab42.models.TextDao;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Lab42Main {
    public static void main(String[] args) throws Exception {
        // Текстовый ДАО
        System.out.println("Text DAO-------------------------------------------------------------------------------------");
        TransportUtils.setDaoFactory(new TextDao());
        testDao();
        // Сериализация
        System.out.println("Serializable DAO-------------------------------------------------------------------------------------");
        TransportUtils.setDaoFactory(new SerializableDao());
        testDao();
    }

    private static void testDao() throws Exception {
        IntStream.rangeClosed(0, 2)
                .forEach(index -> {
                    try {
                        TransportUtils.save(
                                new Car(
                                        String.format("Car Make %s", index), index + 1
                                )
                        );
                    } catch (DuplicateModelNameException | IOException e) {
                        e.printStackTrace();
                    }
                });

        List<Transport> transportList = TransportUtils.getAll();
        printTransports(transportList);

        System.out.println("---------------------Delete Car Make 2");
        TransportUtils.removeByCarMake("Car Make 2");
        printTransports(TransportUtils.getAll());

        System.out.println("---------------------Print Car Make 1");
        System.out.println(
                TransportUtils.toColumnString(
                        TransportUtils.getByCarMake("Car Make 1")
                )
        );
    }

    private static void printTransports(List<Transport> transportList) {
        transportList.forEach(el -> {
            System.out.println(TransportUtils.toColumnString(el));
        });
    }

}
