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
        System.out.println("Задание для машины");
        Transport car = new Car("Мой бренд", 3);
        printDefaultTask(car);

        System.out.println("\nЗадание для мотоцикла");
        Transport motorcycle = new Motorcycle("Бренд мотоцикла", 3);
        printDefaultTask(motorcycle);

        System.out.println("Демонстрация статического класса");

        System.out.println("Демонстрация класса автомобиль");
        printStaticClass(car);

        System.out.println("Демонстрация класса мотоцикл");
        printStaticClass(motorcycle);

        System.out.println("Демонстрация фабрики");
        MotorcycleFactory motorcycleFactory = new MotorcycleFactory();
        CarFactory carFactory = new CarFactory();

        System.out.println("Фабрика машины");
        Car carInstance = (Car) printFactoryMethod(carFactory);

        System.out.println("Фабрика мотоцикл");
        Motorcycle motorCycleInstance = (Motorcycle) printFactoryMethod(motorcycleFactory);

        System.out.println("Демонстрируем прототип");
        System.out.println("Демонстрация прототипа машины");
        prototypePrintMethod(carInstance);

        System.out.println("Демонстрация прототипа мотоцикла");
        prototypePrintMethod(motorCycleInstance);

    }

    public static void prototypePrintMethod(Transport transport) throws CloneNotSupportedException, DuplicateModelNameException, NoSuchModelNameException, NoSuchModelNameException {
        Transport clone = transport.clone();
        clone.setModel("Бренд клон");
        clone.setModelNameByModelName("Модель номер: 3", "Я твой клон");
        clone.removeModelByModelName("Модель номер: 4");

        System.out.println("Исходный объект");
        TransportUtils.printNames(transport);

        System.out.println("Склонированный Объект с измененым брендом и именем одной из моделей");
        TransportUtils.printNames(clone);
    }

    public static Transport printFactoryMethod(TransportFactory transportFactory) throws DuplicateModelNameException {
        TransportUtils.setFactory(transportFactory);

        Transport transport = TransportUtils.createInstance("Бренд фабрики", 5);
        TransportUtils.printNames(transport);
        TransportUtils.printPrices(transport);

        return transport;
    }

    public static void printStaticClass(Transport transport) {
        System.out.println("Распечатка всех моделей");
        TransportUtils.printNames(transport);

        System.out.println("Распечатка всех цен");
        TransportUtils.printPrices(transport);

        System.out.println("Среднее арифметическое");
        System.out.printf("Среднее арифметическое: %s\n", TransportUtils.getAveragePrice(transport));
    }

    public static void printDefaultTask(Transport transport) throws DuplicateModelNameException, NoSuchModelNameException {
        System.out.println("Получение бренда");
        System.out.printf("Марка автомобился: %s\n", transport.getModel());

        System.out.println("Установка нового бренда");
        transport.setModel("Новый бренд");
        System.out.printf("Марка автомобился: %s\n", transport.getModel());

        System.out.println("Проверка всех моделей");
        TransportUtils.printNames(transport);

        System.out.println("Установка нового имени для модели");
        String newModelName = "Новая модель";
        transport.setModelNameByModelName("Модель номер: 1", newModelName);
        TransportUtils.printNames(transport);

        System.out.println("Устнановка новой цены для модели");
        System.out.printf("Цена модели %s: %s\n", newModelName, transport.getPriceByModelName(newModelName));
        transport.setPriceByModelName(newModelName, 12412);
        System.out.printf("Цена модели %s: %s\n", newModelName, transport.getPriceByModelName(newModelName));

        System.out.println("Проверка всех печати всех цен");
        TransportUtils.printPrices(transport);

        System.out.println("Добавление новой модели");
        String addModelName = "Добавленная модель";
        double addModelPrice = 123;
        transport.addNewModel(addModelName, addModelPrice);
        TransportUtils.printNames(transport);

        System.out.println("Удаление добавленной модели");
        transport.removeModelByModelName(addModelName);
        TransportUtils.printNames(transport);

        System.out.println("Исключение нет модели");
        try {
            transport.getPriceByModelName("test");
        } catch (NoSuchModelNameException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("Исключение модель с таким именем уже существует");
        try {
            transport.setModelNameByModelName(newModelName, newModelName);
        } catch (DuplicateModelNameException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("Исключение неверной цены");
        try {
            transport.setPriceByModelName(newModelName, -123123);
        } catch (ModelPriceOutOfBoundsException exception) {
            exception.printStackTrace(System.out);
        }
    }

}
