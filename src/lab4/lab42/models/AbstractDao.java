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
    public void add(Transport vehicle) throws IOException {
        DaoUtils.add(vehicle, fileService);
    }

    @Override
    public Transport getByBrandName(String brandName) throws Exception {
        return DaoUtils.getByBrandName(brandName, fileService);
    }

    @Override
    public void remove(String brandName) throws Exception {
        DaoUtils.remove(brandName, fileService);
    }

    @Override
    public List<Transport> getAll() throws IOException {
        return DaoUtils.getAll(fileService);
    }

}
