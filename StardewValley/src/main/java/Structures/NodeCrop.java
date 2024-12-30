package Structures;

import model.Crop;

public class NodeCrop {

    private Crop crop;

    private NodeCrop next;

    public NodeCrop(Crop crop) {
        this.crop = crop;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public NodeCrop getNext() {
        return next;
    }

    public void setNext(NodeCrop next) {
        this.next = next;
    }
}
