package lab3.lab31.models;

import lab1.lab12.TransportUtils;
import lab1.lab12.interfaces.Transport;

import java.io.IOException;
import java.io.PrintStream;

public class PrintRowHandler extends DefaultHandler{

    public PrintRowHandler() { }

    @Override
    public void handle(Transport transport, String fileName) {
        if (transport.getSize() > 3) {
            this.nextHandler.handle(transport, fileName);
            return;
        }

        try (PrintStream printStream = new PrintStream(fileName)) {
            printStream.println(TransportUtils.toRowString(transport));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
