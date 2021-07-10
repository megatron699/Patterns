package lab2.lab22;

import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.exceptions.NoSuchModelNameException;
import lab1.lab12.interfaces.Transport;
import lab3.lab38.interfaces.Visitor;

public class TransportSynchronizedDecorator implements Transport {
    private final Transport transport;

    public TransportSynchronizedDecorator(Transport transport) {
        this.transport = transport;
    }

    @Override
    public synchronized String getCarMake() {
        return transport.getCarMake();
    }

    @Override
    public synchronized void setCarMake(String brand) {
        transport.setCarMake(brand);
    }

    @Override
    public synchronized String[] getModelsNames() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return transport.getModelsNames();
    }

    @Override
    public synchronized void setModelNameByModelName(String findModelName, String newModelName) throws NoSuchModelNameException, DuplicateModelNameException {
        transport.setModelNameByModelName(findModelName, newModelName);
    }

    @Override
    public synchronized double getPriceByModelName(String modelName) throws NoSuchModelNameException {
        return transport.getPriceByModelName(modelName);
    }

    @Override
    public synchronized void setPriceByModelName(String modelName, double newPrice) throws NoSuchModelNameException {
        transport.setPriceByModelName(modelName, newPrice);
    }

    @Override
    public synchronized double[] getModelsPrices() {
        return transport.getModelsPrices();
    }

    @Override
    public synchronized void addNewModel(String modelName, double price) throws DuplicateModelNameException {
        transport.addNewModel(modelName, price);
    }

    @Override
    public synchronized void removeModelByModelName(String modelName) throws NoSuchModelNameException {
        transport.removeModelByModelName(modelName);
    }

    @Override
    public synchronized Transport clone() throws CloneNotSupportedException {
        return transport.clone();
    }

    @Override
    public synchronized int getSize() {
        return transport.getSize();
    }

    @Override
    public synchronized void accept(Visitor visitor) {
        transport.accept(visitor);
    }


}
