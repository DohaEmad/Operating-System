package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Partition> partitions = new ArrayList<>();
    public static ArrayList<Process> processes = new ArrayList<>();
    public static int partitionsSize = 0;
    public static int processesSize = 0;

    public static void main(String[] args) {
        // while(true){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Number of Partition: ");
        partitionsSize = input.nextInt();
        for (int i = 0; i < partitionsSize; i++) {
            System.out.println("Enter Partition Name: ");
            String name = input.next();
            System.out.println("Enter Partition Size: ");
            Double size = input.nextDouble();
            Partition partition = new Partition(name, size, false);
            partitions.add(partition);
        }

        System.out.println("Enter Number of Processes: ");
        processesSize = input.nextInt();
        for (int i = 0; i < processesSize; i++) {
            System.out.println("Enter Process Name: ");
            String name = input.next();
            System.out.println("Enter Process Size: ");
            Double size = input.nextDouble();
            Process process = new Process(name, size);
            processes.add(process);
        }

        System.out.println("Select the policy you want to apply:\n" +
                "1. First fit\n" +
                "2. Best fit\n" +
                "3. Worst fit\n" +
                "Select policy:\n");
        int choice = input.nextInt();

        if (choice == 1) {
            FirstFit firstFit = new FirstFit();
            firstFit.apply();
            showPartitions();
            showProcesses();
            System.out.println("Do you want to compact?\n" +
                    "1.yes \n" +
                    "2.no");
            int compactChoice = input.nextInt();
            if (compactChoice == 1) {
                Compaction compaction = new Compaction();
                compaction.apply();
                firstFit.apply();
                showPartitions();
                showProcesses();
            } else if (compactChoice != 2) {
                System.out.println("Invalid Choice");
            }
        } else if (choice == 2) {
            BestFit bestFit = new BestFit();
            bestFit.apply();
            showPartitions();
            showProcesses();

            System.out.println("Do you want to compact?\n" +
                    "1.yes \n" +
                    "2.no");
            int compactChoice = input.nextInt();
            if (compactChoice == 1) {
                Compaction compaction = new Compaction();
                compaction.apply();
                bestFit.apply();
                showPartitions();
                showProcesses();
            } else if (compactChoice != 2) {
                System.out.println("Invalid Choice");
            }
        } else if (choice == 3) {
            WorstFit worstFit = new WorstFit();
            worstFit.apply();
            showPartitions();
            showProcesses();

            System.out.println("Do you want to compact?\n" +
                    "1.yes \n" +
                    "2.no");
            int compactChoice = input.nextInt();
            if (compactChoice == 1) {
                Compaction compaction = new Compaction();
                compaction.apply();
                worstFit.apply();
                showPartitions();
                showProcesses();
            } else if (compactChoice != 2) {
                System.out.println("Invalid Choice");
            }
        } else {
            System.out.println("Invalid Choice");
            //break;
        }
    }

    //}
    public static void showProcesses() {
        int n = processes.size();
        for (int i = 0; i < n; i++) {
            if (processes.get(i).getPartitionName().equals(""))
                processes.get(i).show();
        }
    }

    public static void showPartitions() {
        int n = partitions.size();
        for (int i = 0; i < n; i++) {
            partitions.get(i).show();
        }
    }
}
/*


6
Partition0 90
Partition1 20
Partition2 5
Partition3 30
Partition4 120
Partition5 80

4
Process1 15
Process2 90
Process3 30
Process4 100



*/
