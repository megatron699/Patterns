package lab3.lab31.interfaces;

import lab1.lab12.interfaces.Transport;

public interface ChainOfResponsibility {
    void setNext(ChainOfResponsibility nextChain);

    void handle(Transport transport, String fileName);
}
