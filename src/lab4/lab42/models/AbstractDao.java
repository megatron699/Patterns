package lab4.lab42.models;

import lab1.lab12.interfaces.Transport;
import lab4.lab42.interfaces.DaoFactory;
import lab4.lab42.interfaces.FileService;
import lab4.lab42.services.DaoUtils;

import java.io.IOException;
import java.util.List;

public abstract class AbstractDao implements DaoFactory {
    protected String fileName;
    protected FileService fileService;

    @Override
    public void add(Transport transport) throws IOException {
        DaoUtils.add(transport, fileService);
    }

    @Override
    public Transport getByCarMake(String carMake) throws Exception {
        return DaoUtils.getByCarMake(carMake, fileService);
    }

    @Override
    public void remove(String carMake) throws Exception {
        DaoUtils.remove(carMake, fileService);
    }

    @Override
    public List<Transport> getAll() throws IOException {
        return DaoUtils.getAll(fileService);
    }

}
