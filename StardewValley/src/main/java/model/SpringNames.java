package model;

import exceptions.InvalidIntInputException;

public enum SpringNames {
    //Literals
    GARLIC("Garlic"),
    BLUE("Blue Jazz"),
    URICE("Unmilled Rice"),
    PARSNIP("Parsnip");

    //Attributes
    private final String description;

    //Methods

    public static String[] getSpringNames(){
        SpringNames[] names = values();
        String[] descriptions = new String[names.length];
        for(int i = 0; i < names.length; i++){
            descriptions[i] = names[i].getDescription();
        }
        return descriptions;
    }

    public static SpringNames intToSpringName(int intSpringName) throws InvalidIntInputException{
        SpringNames name = null;

        switch(intSpringName){
            case 1: name = GARLIC; break;
            case 2: name = BLUE; break;
            case 3: name = URICE; break;
            case 4: name = PARSNIP; break;
            default:
                throw new InvalidIntInputException("Please enter a number from the provided list of crop names.");
        }

        return name;
    }

    //Constructor

    private SpringNames(String description){
        this.description = description;
    }

    //Getters

    public String getDescription() {
        return description;
    }
}
