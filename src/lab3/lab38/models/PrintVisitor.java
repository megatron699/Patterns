package lab3.lab38.models;

import lab1.lab12.TransportUtils;
import lab1.lab12.models.Car;
import lab1.lab12.models.Motorcycle;
import lab3.lab38.interfaces.Visitor;

public class PrintVisitor implements Visitor {
    @Override
    public void visitCar(Car car) {
        System.out.println(TransportUtils.toRowString(car));
    }

    @Override
    public void visitMotorcycle(Motorcycle motorcycle) {
        System.out.println(TransportUtils.toColumnString(motorcycle));
    }

}
