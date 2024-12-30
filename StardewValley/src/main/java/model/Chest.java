package model;

import Structures.LinkedListSlot;
import Structures.NodeSlot;
import comparators.*;
import exceptions.FailedSearchException;
import exceptions.FullChestException;
import exceptions.InvalidIntInputException;

import java.util.Comparator;

public class Chest {

    //Attributes
    private String name;
    private CropType classification;
    private OrderType order;
    private OrganizationType organization;

    //Associations
    private LinkedListSlot slots;

    //Methods

    public String addCrop(Crop crop) throws FullChestException {
        return slots.add(crop);
    }


    //To make a comparator based on the order and organization
    private Comparator<NodeSlot> getComparator() {
        Comparator<NodeSlot> comparator = null;

        switch (organization) {
            case NAME:
                comparator = new NameComparator(order == OrderType.ASCENDING);
                break;
            case CROPTYPE:
                comparator = new CropTypeComparator(order == OrderType.ASCENDING);
                break;
            case DAYSGROWTH:
                comparator = new DaysGrowthComparator(order == OrderType.ASCENDING);
                break;
        }

        return comparator;
    }

    public void sortChest(){
        slots.insertionSort(getComparator());
    }

    //Search for a Crop
    public String searchCrop(String cropName) throws FailedSearchException {
        return slots.searchCrop(cropName);
    }


    //Constructor

    public Chest(String name, int intClassification) throws InvalidIntInputException {
        this.name = name;
        this.classification = CropType.intToCropType(intClassification);
        this.slots = new LinkedListSlot();
    }

    //Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CropType getClassification() {
        return classification;
    }

    public void setClassification(CropType classification) {
        this.classification = classification;
    }

    public OrderType getOrder() {
        return order;
    }

    public void setOrder(int intOrder) throws InvalidIntInputException {
        this.order = OrderType.intToOrderType(intOrder);
    }

    public OrganizationType getOrganization() {
        return organization;
    }

    public void setOrganization(int intOrganization) throws InvalidIntInputException {
        this.organization = OrganizationType.intToOrganizationType(intOrganization);
    }

    public LinkedListSlot getSlots() {
        return slots;
    }

}
