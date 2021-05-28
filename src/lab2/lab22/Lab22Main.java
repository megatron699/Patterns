package lab2.lab22;

import lab1.lab12.TransportUtils;
import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.interfaces.Transport;
import lab1.lab12.models.Car;

public class Lab22Main {
    public static void main(String[] args) throws DuplicateModelNameException {
        Car carInstance = new Car("Мой бренд", 5);

        System.out.println("Декоратор");
        Transport decorator = TransportUtils.synchronizedTransport(carInstance);

        new Thread(() -> {
            System.out.println("Поток 1");
            TransportUtils.printNames(decorator);
        }).start();
        new Thread(() -> {
            System.out.println("Поток 2");
            TransportUtils.printNames(decorator);
        }).start();

    }
}
