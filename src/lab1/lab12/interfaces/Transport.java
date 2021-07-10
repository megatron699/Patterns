package lab1.lab12.interfaces;

import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.exceptions.NoSuchModelNameException;
import lab3.lab38.interfaces.Visitor;

public interface Transport extends Cloneable {
        String getCarMake();

        void setCarMake(String carMake);

        String[] getModelsNames();

        void setModelNameByModelName(String findModelName, String newModelName) throws NoSuchModelNameException, DuplicateModelNameException;

        double getPriceByModelName(String modelName) throws NoSuchModelNameException;

        void setPriceByModelName(String modelName, double newPrice) throws NoSuchModelNameException;

        double[] getModelsPrices();

        void addNewModel(String modelName, double price) throws DuplicateModelNameException;

        void removeModelByModelName(String modelName) throws NoSuchModelNameException;

        Transport clone() throws CloneNotSupportedException;

        int getSize();

        void accept(Visitor visitor);
}
