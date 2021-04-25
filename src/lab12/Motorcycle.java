package lab12;

public class Motorcycle {

    private int size = 0;
    private Model head = new Model();


    private class Model {
        String modelName = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;
    }


    {
        head.prev = head;
        head.next = head;
    }



}
