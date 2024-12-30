package model;

import exceptions.InvalidIntInputException;

public enum OrganizationType {
    //Literals
    NAME("Name"),
    CROPTYPE("Crop Type"),
    DAYSGROWTH("Days Growth");

    //Attributes
    public final String description;

    //Methods

    public static String[] getOrganizationTypes(){
        OrganizationType[] values = values();
        String[] descriptions = new String[values.length];
        for(int i = 0; i < values.length; i++){
            descriptions[i] = values[i].getDescription();
        }
        return descriptions;
    }

    public static OrganizationType intToOrganizationType(int intOrganizationType) throws InvalidIntInputException {
        OrganizationType value = null;

        switch(intOrganizationType){
            case 1: value= NAME; break;
            case 2: value= CROPTYPE; break;
            case 3: value= DAYSGROWTH; break;
            default:
                throw new InvalidIntInputException("Please enter a number from the provided list of organization types.");
        }

        return value;
    }

    //Constructor

    private OrganizationType(String description) {
        this.description = description;
    }

    //Getters

    public String getDescription() {
        return description;
    }
}
