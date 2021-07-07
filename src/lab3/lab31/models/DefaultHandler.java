package lab3.lab31.models;

import lab3.lab31.interfaces.ChainOfResponsibility;

public abstract class DefaultHandler implements ChainOfResponsibility {
    protected ChainOfResponsibility nextHandler;

    @Override
    public void setNext(ChainOfResponsibility nextChain) {
        this.nextHandler = nextChain;
    }
}
