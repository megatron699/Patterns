package lab1.lab12.interfaces;

import lab1.lab12.exceptions.DuplicateModelNameException;

public interface TransportFactory {
    Transport createInstance(String modelName, int countModel) throws DuplicateModelNameException;
}
