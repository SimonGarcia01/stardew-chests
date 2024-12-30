package Structures;

import exceptions.FullSlotException;
import model.Crop;

public class LinkedListCrop {

    private int cropCounter;
    private static final int MAX_CROPS = 25;

    private NodeCrop first;

    public LinkedListCrop() {
        //Explicit empty constructor
        cropCounter = 0;
    }

    public String add(Crop crop) throws FullSlotException {

        if(cropCounter >= MAX_CROPS){
            throw new FullSlotException();
        }

        cropCounter++;

        NodeCrop temp = new NodeCrop(crop);

        if(first == null){
            first = temp;
        } else {
            NodeCrop current = first;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(temp);
        }

        return "The crop has been added successfully.";
    }

    public NodeCrop getFirst() {
        return first;
    }

}
