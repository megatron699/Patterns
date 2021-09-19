package lab4.lab42.services;

import lab1.lab12.interfaces.Transport;
import lab4.lab42.interfaces.FileService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DaoUtils {
    public static void add(Transport transport, FileService fileService) throws IOException {
        List<Transport> list = fileService.getAll();
        list.add(transport);
        fileService.saveAll(list);
    }

    public static Transport getByCarMake(String carMake, FileService fileService) throws Exception {
        Transport transport = fileService.getAll().stream().filter(el -> el.getCarMake().equals(carMake)).findFirst().get();
        if (transport == null) {
            throw new Exception("No such car make");
        }

        return transport;
    }

    public static void remove(String carMake, FileService fileService) throws Exception {
        List<Transport> transportList = fileService.getAll();
        Transport transport = transportList.stream().filter(el -> el.getCarMake().equals(carMake)).findFirst().get();
        if (transport == null) {
            throw new Exception("No such car make");
        }
        fileService.saveAll(transportList.stream()
                .filter(el -> !el.getCarMake().equals(carMake)).collect(Collectors.toList())
        );
    }

    public static List<Transport> getAll(FileService fileService) throws IOException {
        return fileService.getAll();
    }

}
