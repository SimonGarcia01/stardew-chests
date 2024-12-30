package model;

import exceptions.InvalidDaysGrowthException;
import exceptions.InvalidIntInputException;

public class NonSeasonalCrop extends Crop {
    //Attributes
    private NonSeasonalNames name;

    //Associations

    //Methods

    //Display NonSeasonal Names

    public static String getStringNonSeasonalNames(){
        String message = "Available NonSeasonal Names:";

        String[] nonSeasonalNames = NonSeasonalNames.getNonSeasonalNames();

        for (int n = 0; n < nonSeasonalNames.length; n++){
            message += String.format("\n\t%d. %s", (n+1), nonSeasonalNames[n]);
        }

        return message;
    }

    @Override
    public String toString(){
        return String.format("\n\tCrop Type: Non-Seasonal Crop\n\tDays of Growth: %d\n\tName: %s", this.getDaysGrowth(), this.getName().getDescription());
    }

    //Constructor
    public NonSeasonalCrop(int intName, int daysGrowth) throws InvalidDaysGrowthException, InvalidIntInputException {
        super(daysGrowth);

        if(intName != 2 && (daysGrowth < 1 || daysGrowth > 28)){
            throw new InvalidDaysGrowthException("The days of growth must be between 1 and 28.");
        }

        this.name = NonSeasonalNames.intToNonSeasonalName(intName);
    }

    //Getter and setters
    public NonSeasonalNames getName() {
        return name;
    }

    public void setName(NonSeasonalNames name) {
        this.name = name;
    }
}
