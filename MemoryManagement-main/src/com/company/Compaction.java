package com.company;

import java.util.ArrayList;

public class Compaction {
    public void apply() {
        ArrayList<Partition> partitions = Main.partitions;
        int n = partitions.size();
        double size = 0;
        for (int i = n - 1; i > 0; i--) {
            if (!partitions.get(i).isBusy()) {
                size += partitions.get(i).getSpace();
                partitions.remove(i);
            }
        }
        if (size > 0) {
            Partition partition = new Partition("Partition" + (Main.partitionsSize++), size, false);
            partitions.add(partition);
        }
        Main.partitions = partitions;

    }
}
