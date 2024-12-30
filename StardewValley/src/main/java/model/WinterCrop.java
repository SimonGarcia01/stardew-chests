package model;

import exceptions.InvalidDaysGrowthException;
import exceptions.InvalidIntInputException;

public class WinterCrop extends Crop {
    //Attributes
    private WinterNames name;

    //Methods

    public static String getStringCropNames(){
        String message = "Available Spring Crops:";
        String[] names = WinterNames.getWinterNames();

        for(int n = 0; n < names.length; n++){
            message += String.format("\n\t%d: %s", (n+1), names[n]);
        }
        return message;
    }

    @Override
    public String toString(){
        return String.format("\n\tCrop Type: Winter Crop\n\tDays of Growth: %d\n\tName: %s", this.getDaysGrowth(), this.getName());
    }

    //Constructor
    public WinterCrop(int intName, int daysGrowth) throws InvalidDaysGrowthException, InvalidIntInputException {
        super(daysGrowth);

        if(daysGrowth < 1 || daysGrowth > 28){
            throw new InvalidDaysGrowthException("The days of growth must be between 1 and 28.");
        }

        this.name = WinterNames.intToWinterName(intName);
    }

    //Getter and setters
    public WinterNames getName() {
        return name;
    }

    public void setName(WinterNames name) {
        this.name = name;
    }
}
