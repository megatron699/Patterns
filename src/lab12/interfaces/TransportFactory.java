package lab12.interfaces;

import lab12.exceptions.DuplicateModelNameException;

public interface TransportFactory {
    Transport createInstance(String modelName, int countModel) throws DuplicateModelNameException;
}
