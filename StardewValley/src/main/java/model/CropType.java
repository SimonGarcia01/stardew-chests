package model;

import exceptions.InvalidIntInputException;

public enum CropType {
    //Literals
    CROP("Any type of crop"),
    SPRING("Spring Crops"),
    SUMMER("Summer Crops"),
    AUTUMN("Autumn Crops"),
    WINTER("Winter Crops"),
    NONSEASONAL("Non Seasonal Crops and others");
    
    //Attributes
    public final String description;
    
    //Methods

    public static String[] getCropTypes(){
        CropType[] types = values();
        String[] descriptions = new String[types.length];
        for(int i = 0; i < types.length; i++){
            descriptions[i] = types[i].getDescription();
        }
        return descriptions;
    }

    public static CropType intToCropType(int intCropType) throws InvalidIntInputException {
        CropType type = null;

        switch(intCropType){
            case 1: type = CROP; break;
            case 2: type = SPRING; break;
            case 3: type = SUMMER; break;
            case 4: type = AUTUMN; break;
            case 5: type = WINTER; break;
            case 6: type = NONSEASONAL; break;
            default:
                throw new InvalidIntInputException("Please enter a number from the provided list of order types.");
        }

        return type;
    }
    
    //Constructor
    
    private CropType(String description) {
        this.description = description;
    }
    
    //Getter
    
    public String getDescription() {
        return description;
    }
}