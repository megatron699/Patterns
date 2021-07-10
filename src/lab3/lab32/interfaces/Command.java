package lab3.lab32.interfaces;

import lab1.lab12.models.Car;

import java.io.Writer;

public interface Command {
    void executeCommand(Car car, Writer outputStream);
}
