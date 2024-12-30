package model;

import exceptions.InvalidIntInputException;

public enum SummerNames {
    POPPY("Poppy"),
    BLUE("Blue Berry"),
    STAR("Starfruit"),
    HOT("Hot Pepper");

    //Attributes
    private final String description;

    //Methods

    public static String[] getSummerNames(){
        SummerNames[] names = values();
        String[] descriptions = new String[names.length];
        for(int i = 0; i < names.length; i++){
            descriptions[i] = names[i].getDescription();
        }
        return descriptions;
    }

    public static SummerNames intToSummerName(int intSummerName) throws InvalidIntInputException {
        SummerNames name = null;

        switch(intSummerName){
            case 1: name = POPPY; break;
            case 2: name = BLUE; break;
            case 3: name = STAR; break;
            case 4: name = HOT; break;
            default:
                throw new InvalidIntInputException("Please enter a number from the provided list of crop names.");
        }

        return name;
    }

    //Constructor

    private SummerNames(String description){
        this.description = description;
    }

    //Getters

    public String getDescription() {
        return description;
    }
}
