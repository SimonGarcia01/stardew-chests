package model;

import exceptions.InvalidIntInputException;

public enum NonSeasonalNames {
    //Literals
    FIBER("Fiber"),
    ANCIENT("Ancient Fruit"),
    QI("Qi Fruit");

    //Attributes
    private final String description;

    //Methods

    public static String[] getNonSeasonalNames(){
        NonSeasonalNames[] names = values();
        String[] descriptions = new String[names.length];
        for(int i = 0; i < names.length; i++){
            descriptions[i] = names[i].getDescription();
        }
        return descriptions;
    }

    public static NonSeasonalNames intToNonSeasonalName(int intNonSeasonalName) throws InvalidIntInputException {
        NonSeasonalNames name = null;

        switch(intNonSeasonalName){
            case 1: name = FIBER; break;
            case 2: name = ANCIENT; break;
            case 3: name = QI; break;
            default:
                throw new InvalidIntInputException("Please enter a number from the provided list of crop names.");
        }

        return name;
    }

    //Constructor

    private NonSeasonalNames(String description){
        this.description = description;
    }

    //Getters

    public String getDescription() {
        return description;
    }
}
