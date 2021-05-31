package lab1.lab12.exceptions;

public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException() {
        super("Price of the model must be >=0.");
    }

}
