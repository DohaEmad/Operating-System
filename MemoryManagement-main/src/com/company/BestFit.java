package com.company;

import java.util.ArrayList;

public class BestFit {


    public void apply() {
        ArrayList<Partition> partitions = Main.partitions;
        ArrayList<Process> processes = Main.processes;
        int numOfProcesses = processes.size();
        int numOfPartitions = partitions.size();
        for (int i = 0; i < numOfProcesses; i++) {
            double minDiff = Double.MAX_VALUE;
            int bestIdx = -1;
            for (int j = 0; j < numOfPartitions; j++) {
                if (!partitions.get(j).isBusy() && processes.get(i).getPartitionName().equals("")) {
                    if (partitions.get(j).getSpace() >= processes.get(i).getSpace()) {
                        double dif = partitions.get(j).getSpace() - processes.get(i).getSpace();
                        if (minDiff > dif) {
                            minDiff = dif;
                            bestIdx = j;
                        }
                    }
                }
            }
            if (bestIdx != -1) {
                processes.get(i).setPartitionName(partitions.get(bestIdx).getName());
                partitions.get(bestIdx).setBusy(true);
                partitions.get(bestIdx).setProcessName(processes.get(i).getName());
                if (minDiff > 0) {
                    Partition newPartition = new Partition("Partition" + (Main.partitionsSize++), minDiff, false);
                    newPartition.setExternal(true);
                    addPartition(bestIdx, newPartition);
                    partitions.get(bestIdx).setSpace(processes.get(i).getSpace());


                }

            }

        }


        Main.partitions = partitions;
        Main.processes = processes;


    }

    public void addPartition(int idx, Partition partition) {
        ArrayList<Partition> partitions = Main.partitions;
        Partition temp = new Partition("", 0.0, false);
        partitions.add(temp);

        for (int i = partitions.size() - 1; i >= idx && !partitions.get(i).getFrom().equals(partitions.get(idx).getName()); i--) {
            partitions.get(i).deepCopy(partitions.get(i - 1));
        }
        partitions.get(idx + 1).deepCopy(partition);

    }
}
