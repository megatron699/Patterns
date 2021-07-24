package lab4.lab42.services;

import lab1.lab12.interfaces.Transport;
import lab4.lab42.interfaces.FileService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileSerializableUtils implements FileService {
    private String fileName;

    public FileSerializableUtils(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void saveAll(List<Transport> transportList) throws IOException {
        File fileDao = new File(fileName);
        fileDao.createNewFile();

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileDao))) {
            objectOutputStream.writeObject(transportList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transport> getAll() {
        ArrayList<Transport> transportArrayList = new ArrayList<>();
        File fileDao = new File(fileName);
        try {
            fileDao.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileDao))) {
            Object readObject = objectInputStream.readObject();
            if (readObject == null) {
                return transportArrayList;
            }

            transportArrayList = (ArrayList<Transport>) readObject;
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return transportArrayList;
    }
}
