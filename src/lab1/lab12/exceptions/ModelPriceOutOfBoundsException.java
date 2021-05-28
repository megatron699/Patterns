package lab1.lab12.exceptions;

public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException() {
        super("Цена модели должна быть >=0.");
    }

}
