package exceptions;

public class CropChestMismatchException extends Exception{

    //Empty explicit constructor
    public CropChestMismatchException(){}

    //Message constructor
    public CropChestMismatchException(String message){
        super(message);
    }


}
