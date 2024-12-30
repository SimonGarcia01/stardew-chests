package Structures;

import exceptions.FullSlotException;
import model.Crop;

public class NodeSlot {

    private LinkedListCrop stack;

    private NodeSlot next;
    private NodeSlot prev;

    public NodeSlot() {
        this.stack = new LinkedListCrop();
    }

    public String addCrop(Crop crop) throws FullSlotException {
        return stack.add(crop);
    }

    public void setNext(NodeSlot next) {
        this.next = next;
    }

    public NodeSlot getNext() {
        return next;
    }

    public NodeSlot getPrev() {
        return prev;
    }

    public void setPrev(NodeSlot prev) {
        this.prev = prev;
    }

    public LinkedListCrop getStack() {
        return stack;
    }
}
