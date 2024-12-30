package model;

import exceptions.InvalidIntInputException;

public enum WinterNames {
    POWDER("Powdermellon");

    //Attributes
    private final String description;

    //Methods

    public static String[] getWinterNames(){
        WinterNames[] names = values();
        String[] descriptions = new String[names.length];
        for(int i = 0; i < names.length; i++){
            descriptions[i] = names[i].getDescription();
        }
        return descriptions;
    }

    public static WinterNames intToWinterName(int intWinterName) throws InvalidIntInputException {
        WinterNames name = null;

        switch(intWinterName){
            case 1: name = POWDER; break;
            default:
                throw new InvalidIntInputException("Please enter a number from the provided list of crop names.");
        }

        return name;
    }

    //Constructor

    private WinterNames(String description){
        this.description = description;
    }

    //Getters

    public String getDescription() {
        return description;
    }
    
}
