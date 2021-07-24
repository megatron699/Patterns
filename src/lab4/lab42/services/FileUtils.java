package lab4.lab42.services;

import lab1.lab12.TransportUtils;
import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.interfaces.Transport;
import lab4.lab42.interfaces.FileService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class FileUtils implements FileService {
    private String fileName;

    public FileUtils(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void saveAll(List<Transport> transportList) {
        File fileDao = new File(fileName);
        try {
            fileDao.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(fileDao)) {
            transportList.forEach(vehicle -> {
                writer.println(
                        String.format(
                                "Brand:%s;models:%s",
                                vehicle.getCarMake(),
                                TransportUtils.modelListToString(vehicle))
                );
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transport> getAll() throws IOException {
        List<Transport> transportArrayList = new ArrayList<>();

        File fileDao = new File(fileName);
        fileDao.createNewFile();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileDao))) {
            String record = bufferedReader.readLine();
            while (record != null) {
                String[] splitRecord = record.split(Pattern.quote(";"));
                String[] splitBrand = splitRecord[0].split(",");
                String[] splitModels = splitRecord[1].split(",");
                splitModels[0] = splitModels[0].substring(splitModels[0].indexOf(':') + 1);


                Transport vehicle = TransportUtils.createInstance(getValue(splitBrand[0]), 0);

                IntStream.range(0, splitModels.length / 2)
                        .forEach(index -> {
                            try {
                                int mainIndex = index * 2;
                                int brandIndex = mainIndex + 1;

                                vehicle.addNewModel(
                                        getValue(splitModels[mainIndex]),
                                        Double.parseDouble(getValue(splitModels[brandIndex]))
                                );
                            } catch (DuplicateModelNameException e) {
                                e.printStackTrace();
                            }
                        });

                transportArrayList.add(vehicle);
                record = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }

        return transportArrayList;
    }

    private String getValue(String input) {
        return input.substring(input.indexOf(":") + 1);
    }
}
