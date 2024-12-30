package exceptions;

public class FailedSearchException extends Exception {

    //Empty explicit constructor
    public FailedSearchException() {}

    //Message Constructor
    public FailedSearchException(String message) {
        super(message);
    }

}
