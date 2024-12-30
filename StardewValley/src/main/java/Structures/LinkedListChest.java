package Structures;

import exceptions.InvalidIntInputException;
import model.Chest;

public class LinkedListChest {

    private NodeChest first;
    private int chestCounter;

    public LinkedListChest() {
        //Explicit empty constructor
        chestCounter = -1;
    }

    public String add(Chest chest){
        NodeChest temp = new NodeChest(chest);

        if(first == null){
            first = temp;
        } else {
            NodeChest current = first;
            while(current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(temp);
        }

        chestCounter++;
        return "The chest has been saved successfully.";
    }

    public NodeChest getChestPos(int intChestPosition) throws InvalidIntInputException {

        if (intChestPosition < 0 || intChestPosition > chestCounter) {
            throw new InvalidIntInputException("Invalid chest position: " + (intChestPosition + 1) + ".");
        }

        NodeChest current = first;

        for (int position = 0; position < intChestPosition; position++) {
            current = current.getNext();
        }

        return current;
    }

    public NodeChest getFirst() {
        return first;
    }

    public int getChestCounter() {
        return chestCounter;
    }

}
