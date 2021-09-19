package lab4.lab42.interfaces;

import lab1.lab12.interfaces.Transport;

import java.io.IOException;
import java.util.List;

public interface FileService {
    void saveAll(List<Transport> transportList) throws IOException;

    List<Transport> getAll() throws IOException;
}
