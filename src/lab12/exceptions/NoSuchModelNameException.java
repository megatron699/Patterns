package lab12.exceptions;

public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException(String modelName) {
        super(String.format("Модель с именем %s не существует", modelName));
    }
}
