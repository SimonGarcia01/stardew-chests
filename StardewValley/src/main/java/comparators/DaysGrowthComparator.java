package comparators;

import Structures.NodeSlot;
import model.Crop;

import java.util.Comparator;

public class DaysGrowthComparator implements Comparator<NodeSlot> {
    private boolean ascending;

    public DaysGrowthComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(NodeSlot slot1, NodeSlot slot2) {
        Crop crop1 = slot1.getStack().getFirst().getCrop();
        Crop crop2 = slot2.getStack().getFirst().getCrop();

        int daysGrowth1 = crop1.getDaysGrowth();
        int daysGrowth2 = crop2.getDaysGrowth();

        int comparison = Integer.compare(daysGrowth1, daysGrowth2);
        return ascending ? comparison : -comparison;
    }
}
