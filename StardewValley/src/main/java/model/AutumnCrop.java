package model;

import exceptions.InvalidDaysGrowthException;
import exceptions.InvalidIntInputException;

public class AutumnCrop extends Crop {
    //Attributes
    private AutumnNames name;

    //Methods

    public static String getStringCropNames(){
        String message = "Available Spring Crops:";
        String[] names = AutumnNames.getAutumnNames();

        for(int n = 0; n < names.length; n++){
            message += String.format("\n\t%d: %s", (n+1), names[n]);
        }
        return message;
    }

    @Override
    public String toString(){
        return String.format("\n\tCrop Type: Autumn Crop\n\tDays of Growth: %d\n\tName: %s", this.getDaysGrowth(), this.getName().getDescription());
    }

    //Constructor
    public AutumnCrop(int intName, int daysGrowth) throws InvalidDaysGrowthException, InvalidIntInputException {
        super(daysGrowth);

        if(daysGrowth < 1 || daysGrowth > 28){
            throw new InvalidDaysGrowthException();
        }

        this.name = AutumnNames.intToAutumnName(intName);
    }

    //Getter and setters
    public AutumnNames getName() {
        return name;
    }

    public void setName(AutumnNames name) {
        this.name = name;
    }
}
