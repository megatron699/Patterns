package lab1.lab12.models;

import lab1.lab12.TransportUtils;
import lab1.lab12.interfaces.Transport;
import lab1.lab12.exceptions.DuplicateModelNameException;
import lab1.lab12.exceptions.ModelPriceOutOfBoundsException;
import lab1.lab12.exceptions.NoSuchModelNameException;
import lab3.lab38.interfaces.Visitor;

public class Motorcycle implements Transport {

    private Model head = new Model();

    private int size;
    private String carMake;

    {
        head.prev = head;
        head.next = head;
    }

    @Override
    public String getCarMake() {
        return carMake;
    }

    @Override
    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public Motorcycle(String modelName, int modelCount) throws DuplicateModelNameException {
        carMake = modelName;
        size = 0;

        TransportUtils.initialization(this, modelCount);
    }

    @Override
    public String[] getModelsNames() {
        String[] names = new String[size];
        Model first = head.getNext();

        int i = 0;
        while (first != head) {
            names[i++] = first.getModelName();
            first = first.getNext();
        }

        return names;
    }

    @Override
    public void setModelNameByModelName(String findModelName, String newModelName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (getModelByModelName(newModelName) != null) {
            throw new DuplicateModelNameException(newModelName);
        }

        Model findModel = getModelByModelName(findModelName);
        if (findModel == null) {
            throw new NoSuchModelNameException(findModelName);
        }

        findModel.setModelName(newModelName);
    }

    @Override
    public double getPriceByModelName(String modelName) throws NoSuchModelNameException {
        Model findModel = getModelByModelName(modelName);
        if (findModel == null) {
            throw new NoSuchModelNameException(modelName);
        }

        return findModel.getPrice();
    }

    @Override
    public void setPriceByModelName(String modelName, double newPrice) throws NoSuchModelNameException {
        if (newPrice < 0) {
            throw new ModelPriceOutOfBoundsException();
        }

        Model findModel = getModelByModelName(modelName);
        if (findModel == null) {
            throw new NoSuchModelNameException(modelName);
        }

        findModel.setPrice(newPrice);
    }

    @Override
    public double[] getModelsPrices() {
        double[] prices = new double[size];
        Model first = head.getNext();
        int i = 0;

        while (first != head) {
            prices[i++] = first.getPrice();
            first = first.getNext();
        }

        return prices;
    }

    @Override
    public void addNewModel(String modelName, double price) throws DuplicateModelNameException {
        if (getModelByModelName(modelName) != null) {
            throw new DuplicateModelNameException(modelName);
        }

        Model model = new Model(modelName, price);
        model.setNext(head);
        model.setPrev(head.prev);
        head.getPrev().setNext(model);
        head.setPrev(model);

        size += 1;
    }

    @Override
    public void removeModelByModelName(String modelName) throws NoSuchModelNameException {
        Model model = getModelByModelName(modelName);
        if (model == null) {
            throw new NoSuchModelNameException(modelName);
        }

        model.getPrev().setNext(model.getNext());
        model.getNext().setPrev(model.getPrev());

        size -= 1;
    }

    @Override
    public Transport clone() throws CloneNotSupportedException {
        Motorcycle clone = (Motorcycle) super.clone();
        Model cloneHead = new Model();
        clone.head = cloneHead;
        clone.head.setNext(clone.head);
        clone.head.setPrev(clone.head);

        Model originalElement = this.head.getNext();

        while (originalElement != this.head) {
            Model newModel = originalElement.clone();

            newModel.setPrev(cloneHead.getPrev());
            newModel.setNext(cloneHead);

            cloneHead.getPrev().setNext(newModel);
            cloneHead.setPrev(newModel);

            originalElement = originalElement.getNext();
        }

        return clone;

    }

    @Override
    public void accept(Visitor visitor){
        visitor.visitMotorcycle(this);
    }

    private Model getModelByModelName(String modelName) {
        Model first = head.getNext();

        while (first != head && !first.getModelName().equals(modelName)) {
            first = first.getNext();
        }

        return first == head
                ? null
                : first;
    }

    private class Model implements Cloneable {
        String modelName = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;

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

        public Model getPrev() {
            return prev;
        }

        public void setPrev(Model prev) {
            this.prev = prev;
        }

        public Model getNext() {
            return next;
        }

        public void setNext(Model next) {
            this.next = next;
        }

        public Model() {
        }

        public Model(String modelName, double price) {
            this.modelName = modelName;
            this.price = price;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }

    }

    @Override
    public int getSize() {
        return this.size;
    }
}
