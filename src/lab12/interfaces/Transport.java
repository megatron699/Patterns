package lab12.interfaces;

import lab12.exceptions.DuplicateModelNameException;
import lab12.exceptions.NoSuchModelNameException;

public interface Transport extends Cloneable {
        String getModel();

        void setModel(String model);

        String[] getModelsNames();

        void setModelNameByModelName(String findModelName, String newModelName) throws NoSuchModelNameException, DuplicateModelNameException;

        double getPriceByModelName(String modelName) throws NoSuchModelNameException;

        void setPriceByModelName(String modelName, double newPrice) throws NoSuchModelNameException;

        double[] getModelsPrices();

        void addNewModel(String modelName, double price) throws DuplicateModelNameException;

        void removeModelByModelName(String modelName) throws NoSuchModelNameException;

        Transport clone() throws CloneNotSupportedException;

        int getSize();

        //String toString();
}
