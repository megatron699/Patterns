package lab12.models;

import lab12.exceptions.DuplicateModelNameException;
import lab12.interfaces.Transport;
import lab12.interfaces.TransportFactory;

public class MotorcycleFactory implements TransportFactory {
    @Override
    public Transport createInstance(String modelName, int countModel) throws DuplicateModelNameException {
        return new Motorcycle(modelName, countModel);
    }
}
