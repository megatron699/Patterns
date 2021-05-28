package lab12.models;

import lab12.TransportUtils;
import lab12.interfaces.Transport;
import lab12.exceptions.DuplicateModelNameException;
import lab12.exceptions.ModelPriceOutOfBoundsException;
import lab12.exceptions.NoSuchModelNameException;

import java.util.Arrays;

public class Car implements Transport {
    private String model;
    private Model[] models;
    private int size;

    public Car(String model, int modelCount)  throws DuplicateModelNameException{
        this.model = model;
        this.models = new Model[0];

        TransportUtils.initialization(this, modelCount);
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getSize() {
        return size;
    }


    public void setSize(int size) {
        this.size = size;
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


    private int getIndexModelByName(String modelName) {
        int modelIndex = 0;
        while (modelIndex < models.length && !models[modelIndex].modelName.equals(modelName)) {
            modelIndex++;
        }

        return modelIndex == models.length
                ? -1
                : modelIndex;
    }

    protected static class Model implements Cloneable{
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

    }

}

