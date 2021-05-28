package lab1.lab12.models;

import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.interfaces.Transport;
import lab1.lab12.interfaces.TransportFactory;

public class MotorcycleFactory implements TransportFactory {
    @Override
    public Transport createInstance(String modelName, int countModel) throws DuplicateModelNameException {
        return new Motorcycle(modelName, countModel);
    }
}
