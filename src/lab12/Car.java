package lab12;

import java.util.Arrays;

public class Car implements Transport{
    private String model;
    private Model[] models;
    private int size;

    public Car(String model, int modelCount){
        this.model = model;
        this.models = new Model[0];
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    protected static class Model {
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
    }

    public void setModelNameByModelName(String modelName, String newModelName){
        /*if (getIndexModelByName(newModelName) != -1) {
            throw new DuplicateModelNameException(newModelName);
        }*/

        int indexModel = getIndexModelByName(modelName);
        /*if (indexModel == -1) {
            throw new NoSuchModelNameException(findModelName);
        }*/

        models[indexModel].setModelName(newModelName);

    }

    public String[] getModelsNames() {
        String[] names = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            names[i] = models[i].modelName;
        }
        return names;
    }

    public double getModelPriceByModelName(String modelName){
        int indexModel = getIndexModelByName(modelName);
        return models[indexModel].getPrice();
    }

    public void setModelPriceByModelName(String modelName, double newPrice){
        int indexModel = getIndexModelByName(modelName);
        models[indexModel].setPrice(newPrice);
    }

    public double[] getModelsPrices() {
        double[] prices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            prices[i] = models[i].price;
        }
        return prices;
    }

    public void addNewModel(String modelName, double price) {
        int indexModel = getIndexModelByName(modelName);

        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(modelName, price);
        size++;
    }

    public void removeModelByModelName(String modelName) {
        int indexModel = getIndexModelByName(modelName);

        Model[] newModelList = new Model[models.length - 1];

        System.arraycopy(models, 0, newModelList, 0, indexModel);
        System.arraycopy(models, indexModel + 1, newModelList, indexModel, models.length - indexModel - 1);

        models = Arrays.copyOf(newModelList, newModelList.length);
        size--;
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

}

