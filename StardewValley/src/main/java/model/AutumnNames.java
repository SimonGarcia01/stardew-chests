package model;

import exceptions.InvalidIntInputException;

public enum AutumnNames {
    ARTICHOKE("Artichoke"), 
    AMARANTH("Amaranth"), 
    SWEET("Sweet Gem Berry"),
    EGG("Eggplant");

    //Attributes
    private final String description;

    //Methods

    public static String[] getAutumnNames(){
        AutumnNames[] names = values();
        String[] descriptions = new String[names.length];
        for(int i = 0; i < names.length; i++){
            descriptions[i] = names[i].getDescription();
        }
        return descriptions;
    }

    public static AutumnNames intToAutumnName(int intAutumnName) throws InvalidIntInputException {
        AutumnNames name = null;

        switch(intAutumnName){
            case 1: name = ARTICHOKE; break;
            case 2: name = AMARANTH; break;
            case 3: name = SWEET; break;
            case 4: name = EGG; break;
            default:
                throw new InvalidIntInputException("Please enter a number from the provided list of crop names.");
        }

        return name;
    }

    //Constructor

    private AutumnNames(String description){
        this.description = description;
    }

    //Getters

    public String getDescription() {
        return description;
    }
}
