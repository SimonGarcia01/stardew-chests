package model;

import exceptions.InvalidIntInputException;

public enum OrderType {
    //Literals
    ASCENDING("Ascending"),
    DESCENDING("Descending");

    //Attributes
    public final String description;

    //Methods

    public static String[] getOrderTypes(){
        OrderType[] values = values();
        String[] descriptions = new String[values.length];
        for(int i = 0; i < values.length; i++){
            descriptions[i] = values[i].getDescription();
        }
        return descriptions;
    }

    public static OrderType intToOrderType(int intOrderType) throws InvalidIntInputException {
        OrderType value = null;

        switch(intOrderType){
            case 1: value= ASCENDING; break;
            case 2: value= DESCENDING; break;
            default:
                throw new InvalidIntInputException("Please enter a number from the provided list of order types.");
        }

        return value;
    }

    //Constructor

    private OrderType(String description) {
        this.description = description;
    }

    //Getters

    public String getDescription() {
        return description;
    }
}
