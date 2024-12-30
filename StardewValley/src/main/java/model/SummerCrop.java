package model;

import exceptions.InvalidDaysGrowthException;
import exceptions.InvalidIntInputException;

public class SummerCrop extends Crop {
    //Attributes
    private SummerNames name;

    //Methods

    public static String getStringCropNames(){
        String message = "Available Spring Crops:";
        String[] names = SummerNames.getSummerNames();

        for(int n = 0; n < names.length; n++){
            message += String.format("\n\t%d: %s", (n+1), names[n]);
        }
        return message;
    }

    @Override
    public String toString(){
        return String.format("\n\tCrop Type: Summer Crop\n\tDays of Growth: %d\n\tName: %s", this.getDaysGrowth(), this.getName().getDescription());
    }

    //Constructor
    public SummerCrop(int intName, int daysGrowth) throws InvalidDaysGrowthException, InvalidIntInputException {
        super(daysGrowth);

        if(daysGrowth < 1 || daysGrowth > 28){
            throw new InvalidDaysGrowthException("The days of growth must be between 1 and 28.");
        }

        this.name = SummerNames.intToSummerName(intName);
    }

    //Getter and setters
    public SummerNames getName() {
        return name;
    }

    public void setName(SummerNames name) {
        this.name = name;
    }
}
