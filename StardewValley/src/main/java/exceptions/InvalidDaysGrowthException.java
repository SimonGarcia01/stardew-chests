package exceptions;

public class InvalidDaysGrowthException extends Exception {

    //Empty explicit constructor
    public InvalidDaysGrowthException() {}

    //Message constructor
    public InvalidDaysGrowthException(String message) {
        super(message);
    }
}
