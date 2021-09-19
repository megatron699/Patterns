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
            transportList.forEach(transport -> {
                writer.println(
                        String.format(
                                "Car make:%s;models:%s",
                                transport.getCarMake(),
                                TransportUtils.modelListToString(transport))
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


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileDao))) {
            String record = bufferedReader.readLine();
            while (record != null) {
                String[] splitRecord = record.split(Pattern.quote(";"));
                String[] splitCarMake = splitRecord[0].split(",");
                String[] splitModels = splitRecord[1].split(",");
                splitModels[0] = splitModels[0].substring(splitModels[0].indexOf(':') + 1);


                Transport transport = TransportUtils.createInstance(getValue(splitCarMake[0]), 0);

                IntStream.range(0, splitModels.length / 2)
                        .forEach(index -> {
                            try {
                                int mainIndex = index * 2;
                                int brandIndex = mainIndex + 1;

                                transport.addNewModel(
                                        getValue(splitModels[mainIndex]),
                                        Double.parseDouble(getValue(splitModels[brandIndex]))
                                );
                            } catch (DuplicateModelNameException e) {
                                e.printStackTrace();
                            }
                        });

                transportArrayList.add(transport);
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
