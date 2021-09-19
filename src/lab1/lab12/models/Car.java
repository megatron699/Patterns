package lab1.lab12.models;

import lab1.lab12.TransportUtils;
import lab1.lab12.interfaces.Transport;
import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.exceptions.ModelPriceOutOfBoundsException;
import lab1.lab12.exceptions.NoSuchModelNameException;
import lab3.lab32.interfaces.Command;
import lab3.lab38.interfaces.Visitor;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class Car implements Transport, Serializable {
    private String carMake;
    private Model[] models;
    private int size;
    private Command commandPrint;

    public Car(String carMake, int modelCount)  throws DuplicateModelNameException{
        this.carMake = carMake;
        this.models = new Model[0];

        TransportUtils.initialization(this, modelCount);
    }

    public void setPrintCommand(Command commandPrint) {
        this.commandPrint = commandPrint;
    }

    @Override
    public String getCarMake() {
        return carMake;
    }

    @Override
    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setModelNameByModelName(String modelName, String newModelName) throws DuplicateModelNameException, NoSuchModelNameException {
        if (getIndexModelByName(newModelName) != -1) {
            throw new DuplicateModelNameException(newModelName);
        }

        int indexModel = getIndexModelByName(modelName);
        if (indexModel == -1) {
            throw new NoSuchModelNameException(modelName);
        }

        models[indexModel].setModelName(newModelName);

    }
    @Override
    public String[] getModelsNames() {
        String[] names = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            names[i] = models[i].modelName;
        }
        return names;
    }

    @Override
    public double getPriceByModelName(String modelName) throws NoSuchModelNameException {
        int indexModel = getIndexModelByName(modelName);

        if (indexModel == -1) {
            throw new NoSuchModelNameException(modelName);
        }


        return models[indexModel].getPrice();
    }

    @Override
    public void setPriceByModelName(String modelName, double newPrice) throws NoSuchModelNameException {
        if (newPrice < 0) {
            throw new ModelPriceOutOfBoundsException();
        }

        int indexModel = getIndexModelByName(modelName);

        if (indexModel == -1) {
            throw new NoSuchModelNameException(modelName);
        }

        models[indexModel].setPrice(newPrice);
    }

    @Override
    public double[] getModelsPrices() {
        double[] prices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            prices[i] = models[i].price;
        }
        return prices;
    }

    @Override
    public void addNewModel(String modelName, double price) throws DuplicateModelNameException {
        int indexModel = getIndexModelByName(modelName);

        if (indexModel != -1) {
            throw new DuplicateModelNameException(modelName);
        }

        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(modelName, price);
        size++;
    }

    @Override
    public void removeModelByModelName(String modelName) throws NoSuchModelNameException {
        int indexModel = getIndexModelByName(modelName);

        if (indexModel == -1) {
            throw new NoSuchModelNameException(modelName);
        }

        Model[] newModelList = new Model[models.length - 1];

        System.arraycopy(models, 0, newModelList, 0, indexModel);
        System.arraycopy(models, indexModel + 1, newModelList, indexModel, models.length - indexModel - 1);

        models = Arrays.copyOf(newModelList, newModelList.length);
        size--;
    }

    @Override
    public Transport clone() throws CloneNotSupportedException {
        Car clone = (Car) super.clone();
        clone.models = clone.models.clone();
        for (int i = 0; i < clone.models.length; i++) {
            clone.models[i] = clone.models[i].clone();
        }

        return clone;
    }

    public void print(Writer outputStream) throws Exception {
        if (commandPrint == null) {
            throw new Exception("Command not found");
        }

        commandPrint.executeCommand(this, outputStream);
    }


    public CarIterator iterator() {
        return new CarIterator();
    }

    public Memento createMemento() {
        return new Memento(this);
    }

    public void setMemento(Memento memento) throws Exception {
        memento.getAuto(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitCar(this);
    }

    public static class Memento {
        private byte[] carBytes;

        public Memento(Car car) {
            setAuto(car);
        }

        public void setAuto(Car car) {
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                try (ObjectOutputStream dataOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
                    dataOutputStream.writeObject(car);
                    dataOutputStream.flush();
                }

                byteArrayOutputStream.flush();
                carBytes = byteArrayOutputStream.toByteArray();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        public void getAuto(Car newCar) throws Exception {
            if (carBytes == null) {
                throw new Exception("Момента не существует");
            }

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(carBytes)) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
                    newCar.setNewCar((Car) objectInputStream.readObject());
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    protected static class Model implements Cloneable, Serializable {
        private String modelName;
        private double price;

        public Model(String modelName, double price) {
            this.modelName = modelName;
            this.price = price;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }

        @Override
        public String toString() {
            return "Model{" +
                    "modelName='" + modelName + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    private int getIndexModelByName(String modelName) {
        int modelIndex = 0;
        while (modelIndex < models.length && !models[modelIndex].modelName.equals(modelName)) {
            modelIndex++;
        }

        return modelIndex == models.length
                ? -1
                : modelIndex;
    }

    private void setNewCar(Car readObject) {
        this.carMake = readObject.carMake;
        this.models = readObject.models;
    }

    private class CarIterator implements Iterator<Model> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Model next() {
            return models[currentIndex++];
        }

    }



}

