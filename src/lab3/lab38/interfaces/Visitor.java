package lab3.lab38.interfaces;

import lab1.lab12.models.Car;
import lab1.lab12.models.Motorcycle;

public interface Visitor {
    void visitCar(Car car);
    void visitMotorcycle(Motorcycle motorcycle);
}
