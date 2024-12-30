package comparators;

import Structures.NodeSlot;
import model.*;

import java.util.Comparator;

public class NameComparator implements Comparator<NodeSlot> {
    private final boolean ascending;

    public NameComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(NodeSlot slot1, NodeSlot slot2) {
        Crop crop1 = slot1.getStack().getFirst().getCrop();
        Crop crop2 = slot2.getStack().getFirst().getCrop();

        String name1 = getCropName(crop1);
        String name2 = getCropName(crop2);

        int comparison = name1.compareTo(name2);
        return ascending ? comparison : -comparison;
    }

    private String getCropName(Crop crop) {
        String message = "";

        if (crop instanceof NonSeasonalCrop) {
            message = ((NonSeasonalCrop) crop).getName().getDescription();
        } else if (crop instanceof SummerCrop) {
            message = ((SummerCrop) crop).getName().getDescription();
        } else if (crop instanceof SpringCrop) {
            message = ((SpringCrop) crop).getName().getDescription();
        } else if (crop instanceof AutumnCrop) {
            message = ((AutumnCrop) crop).getName().getDescription();
        } else if (crop instanceof WinterCrop) {
            message = ((WinterCrop) crop).getName().getDescription();
        }

        return message;
    }
}