package Structures;

import model.Chest;

public class NodeChest {

    private Chest chest;

    private NodeChest next;

    public NodeChest(Chest chest) {
        this.chest = chest;
    }

    public Chest getChest() {
        return chest;
    }

    public void setChest(Chest chest) {
        this.chest = chest;
    }

    public NodeChest getNext() {
        return next;
    }

    public void setNext(NodeChest next) {
        this.next = next;
    }
}
