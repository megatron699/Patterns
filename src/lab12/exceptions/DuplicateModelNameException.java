package lab12.exceptions;

public class DuplicateModelNameException extends Exception{
    public DuplicateModelNameException(String modelName) {
        super(String.format("Модель с именем %s уже существует.", modelName));
    }
}
