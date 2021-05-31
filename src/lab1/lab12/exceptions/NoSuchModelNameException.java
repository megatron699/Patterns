package lab1.lab12.exceptions;

public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException(String modelName) {
        super(String.format("Model with name %s doesn't exist.", modelName));
    }
}
