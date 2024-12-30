package model;

public abstract class Crop {
    //Attributes
    private int daysGrowth;

    //Associations

    //Methods

    //Constructor
    public Crop(int daysGrowth) {
        this.daysGrowth = daysGrowth;
    }

    //Getters and setters
    public int getDaysGrowth() {
        return daysGrowth;
    }

    public void setDaysGrowth(int daysGrowth) {
        this.daysGrowth = daysGrowth;
    }
}
