package exceptions;

public class FullSlotException extends Exception {

    //Explicit empty constructor
    public FullSlotException() {}

    //Message exception
    public FullSlotException(String message) {
        super(message);
    }

}
