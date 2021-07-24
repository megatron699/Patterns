package lab4.lab42.interfaces;

import lab1.lab12.interfaces.Transport;

import java.io.IOException;
import java.util.List;

public interface DaoFactory {
    void add(Transport transport) throws IOException;

    Transport getByBrandName(String brandName) throws Exception;

    void remove(String brandName) throws Exception;

    List<Transport> getAll() throws IOException;
}
