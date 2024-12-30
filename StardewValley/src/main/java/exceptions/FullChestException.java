package exceptions;

public class FullChestException extends Exception {

    //Empty explicit constructor
    public FullChestException() {}

    //Message constructor
    public FullChestException(String message) {
        super(message);
    }
}
