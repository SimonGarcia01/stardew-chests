package comparators;

import Structures.NodeSlot;
import model.*;

import java.util.Comparator;

public class CropTypeComparator implements Comparator<NodeSlot> {
    private boolean ascending;

    public CropTypeComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(NodeSlot slot1, NodeSlot slot2) {
        Crop crop1 = slot1.getStack().getFirst().getCrop();
        Crop crop2 = slot2.getStack().getFirst().getCrop();

        int priority1 = getCropTypePriority(crop1);
        int priority2 = getCropTypePriority(crop2);

        int comparison = Integer.compare(priority1, priority2);

        return ascending ? comparison : -comparison;
    }

    private int getCropTypePriority(Crop crop) {
        int priority = 0;

        if (crop instanceof SpringCrop) {
            priority = 1;
        } else if (crop instanceof SummerCrop) {
            priority = 2;
        } else if (crop instanceof AutumnCrop) {
            priority = 3;
        } else if (crop instanceof WinterCrop) {
            priority = 4;
        } else if (crop instanceof NonSeasonalCrop) {
            priority = 5;
        }

        return priority;
    }
}
