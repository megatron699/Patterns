package lab3.lab32.models;

import lab1.lab12.TransportUtils;
import lab1.lab12.models.Car;
import lab3.lab32.interfaces.Command;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

public class PrintRowCommand implements Command, Serializable {
    @Override
    public void executeCommand(Car car, Writer outputStream) {
        try {
            outputStream.write(TransportUtils.toRowString(car));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
