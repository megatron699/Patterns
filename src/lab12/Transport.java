package lab12;

public interface Transport {
    public interface Vehicle extends Cloneable {
        String getBrand();

        void setBrand(String brand);

        String[] getModelsName();

        void setModelNameByModelName(String findModelName, String newModelName) /*throws NoSuchModelNameException, DuplicateModelNameException*/;

        double getPriceByModelName(String modelName) /*throws NoSuchModelNameException*/;

        void setPriceByModelName(String modelName, double newPrice) /*throws NoSuchModelNameException*/;

        double[] getArrayPriceModel();

        void addNewModel(String modelName, double price) /*throws DuplicateModelNameException*/;

        void removeModelByModelName(String modelName) /*throws NoSuchModelNameException*/;

        //Vehicle clone() throws CloneNotSupportedException;

        int getSize();

        //String toString();

        //void accept(Visitor visitor);
    }

}
