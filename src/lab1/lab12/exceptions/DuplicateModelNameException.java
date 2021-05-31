package lab1.lab12.exceptions;

public class DuplicateModelNameException extends Exception{
    public DuplicateModelNameException(String modelName) {
        super(String.format("Model with name %s already exists.", modelName));
    }
}
