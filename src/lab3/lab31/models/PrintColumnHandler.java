package lab3.lab31.models;

import lab1.lab12.TransportUtils;
import lab1.lab12.interfaces.Transport;

import java.io.IOException;
import java.io.PrintStream;

public class PrintColumnHandler extends DefaultHandler{
    public PrintColumnHandler() { }
    @Override
    public void handle(Transport transport, String fileName) {
        if (transport.getSize() <= 3) {
            nextHandler.handle(transport,fileName);
            return;
        }

        try (PrintStream printStream = new PrintStream(fileName)) {
            printStream.println(TransportUtils.toColumnString(transport));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
