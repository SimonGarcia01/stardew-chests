package Structures;

import exceptions.FailedSearchException;
import exceptions.FullChestException;
import exceptions.FullSlotException;
import model.*;

import java.util.Comparator;

public class LinkedListSlot {

    private int slotCounter;
    private static final int MAX_SLOTS = 50;

    private NodeSlot first;

    public LinkedListSlot() {
        this.slotCounter = -1;
    }

    public String add(Crop crop) throws FullChestException {
        if (slotCounter >= MAX_SLOTS) {
            throw new FullChestException("The chest is full, it can't save anything else.");
        }

        String message = "";
        String cropDescription = getCropDescription(crop);

        // Check if the list is empty and create the first slot
        if (first == null) {
            // If the first slot is null, create the first slot and add the crop
            first = new NodeSlot();

            try {
                message = first.addCrop(crop);
            } catch (FullSlotException e) {
                //Will not happen since is the first in the stack too
            }
            slotCounter++;
        } else {
            NodeSlot current = first;
            NodeSlot previous = null;
            boolean isAdded = false;

            // Traverse the list and check for a matching crop slot with free space
            while (current != null && !isAdded) {
                LinkedListCrop currentStack = current.getStack();

                // Get the description of the first crop in the current slot
                String firstCropDescription = getCropDescription(currentStack.getFirst().getCrop());

                // If the crop descriptions match, try adding the crop to this slot
                if (firstCropDescription.equals(cropDescription)) {
                    try {
                        message = currentStack.add(crop); // Try adding the crop to the existing crop list
                        isAdded = true; // Crop successfully added
                    } catch (FullSlotException e) {
                        // Slot is full, continue checking other slots
                    }
                }
                previous = current;
                current = current.getNext();
            }

            //If it didn't, then just create a new slot of the new crop

            if(!isAdded){
                current = new NodeSlot();
                current.setPrev(previous);
                previous.setNext(current);
                try{
                    message = current.addCrop(crop);
                    slotCounter++;
                } catch (FullSlotException e) {
                    //Not full since it was just created
                }
            }
        }
        return message;
    }

    private String getCropDescription(Crop crop) {
        String message = "";

        if (crop instanceof SpringCrop) {
            message = ((SpringCrop) crop).getName().getDescription();
        } else if (crop instanceof SummerCrop) {
            message = ((SummerCrop) crop).getName().getDescription();
        } else if (crop instanceof AutumnCrop) {
            message = ((AutumnCrop) crop).getName().getDescription();
        } else if (crop instanceof WinterCrop) {
            message = ((WinterCrop) crop).getName().getDescription();
        } else if (crop instanceof NonSeasonalCrop) {
            message = ((NonSeasonalCrop) crop).getName().getDescription();
        }

        return message;
    }

    public void insertionSort(Comparator<NodeSlot> comparator) {
        if (first == null || first.getNext() == null) {
            return; // List is empty or has only one node
        }

        NodeSlot sortedList = null; // This will be the new sorted list
        NodeSlot current = first;

        while (current != null) {
            // Store the next node to process
            NodeSlot next = current.getNext();

            // Insert current node into sortedList
            if (sortedList == null || comparator.compare(current, sortedList) < 0) {
                // Insert at the beginning
                current.setNext(sortedList);
                sortedList = current;
            } else {
                // Find the insertion point
                NodeSlot sortedCurrent = sortedList;
                while (sortedCurrent.getNext() != null && comparator.compare(current, sortedCurrent.getNext()) >= 0) {
                    sortedCurrent = sortedCurrent.getNext();
                }
                // Insert current node into its proper position
                current.setNext(sortedCurrent.getNext());
                sortedCurrent.setNext(current);
            }

            // Move to the next node
            current = next;
        }

        // Update the first node to be the new sorted list
        first = sortedList;
    }

    public String searchCrop(String cropName) throws FailedSearchException {
        String message = "The crop was not found in the chest";

        NodeSlot searchedCrop = binarySearchCrop(cropName);

        if(searchedCrop == null){
            throw new FailedSearchException("The searched crop wasn't found in the selected chest.");
        }

        message = searchedCrop.getStack().getFirst().getCrop().toString();

        return message;
    }

    public NodeSlot binarySearchCrop(String cropName) {
        int start = 0;
        int end = slotCounter;
        NodeSlot found = null;
        boolean isFound = false;

        while (start <= end && !isFound) {
            int mid = (start + end) / 2;

            // Get the middle node
            NodeSlot midNode = getNodeAtIndex(mid);

            //Middle Node Description
            String midCropDescription = getCropDescription(midNode.getStack().getFirst().getCrop());

            // Compare middle with cropName
            int comparison = midCropDescription.compareTo(cropName);

            if (comparison == 0) {
                found = midNode; // Found the crop
                isFound = true;
            } else if (comparison < 0) {
                // Crop name is larger than the middle crop, search in the right half
                start = mid + 1;
            } else {
                // Crop name is smaller than the middle crop, search in the left half
                end = mid - 1;
            }
        }

        return found; // Crop not found
    }

    private NodeSlot getNodeAtIndex(int index) {
        NodeSlot current = first;
        int count = 0;

        while (current != null && count < index) {
            current = current.getNext();
            count++;
        }

        return current; // This will be null if the index is out of bounds
    }

    public NodeSlot getFirst() {
        return first;
    }

    public int getSlotCounter() {
        return slotCounter;
    }
}
