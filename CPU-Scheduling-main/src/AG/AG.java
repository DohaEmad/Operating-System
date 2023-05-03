package AG;

import java.util.*;

import Process.Process;

public class AG {
    private ArrayList<Process> processes;
    private ArrayList<Process> finalProcesses = new ArrayList<>();
    private ArrayList<Process> readyQueue = new ArrayList<>();
    private Process currProcess = null;
    private int time;
    private HashMap<String, Process> uniqueProcesses = new HashMap<>();
    private ArrayList<ArrayList<Integer>> quantumUpdate = new ArrayList<ArrayList<Integer>>();

    public AG(ArrayList<Process> processes) {
        this.processes = processes;
        for (int i = 0; i < processes.size(); i++) {
            uniqueProcesses.put(processes.get(i).getProcessName(), processes.get(i));
        }
        calculateQuantumUpdate();
        this.time = 0;
        int mnTime = 1000000000;
        int idx = -1;
        for (int i = 0; i < processes.size(); i++) {
            if (mnTime >= processes.get(i).getArrivalTime()) {
                mnTime = processes.get(i).getArrivalTime();
                idx = i;
                currProcess = processes.get(i);
            }
        }
        processes.remove(idx);
        time = mnTime;
        readyQueue.add(currProcess);
        currProcess = null;
    }

    public void getNextReady() {
        int mnTime = 1000000000;
        int idx = -1;
        Process nextReady = null;
        for (int i = 0; i < processes.size(); i++) {
            if (mnTime >= processes.get(i).getArrivalTime()) {
                mnTime = processes.get(i).getArrivalTime();
                idx = i;
                nextReady = processes.get(i);
            }
        }
        if (idx != -1) {
            processes.remove(idx);
            time = mnTime;
            readyQueue.add(nextReady);
        }
    }

    public void calculateQuantumUpdate() {
        ArrayList<Integer> quantums = new ArrayList<Integer>();
        for (Map.Entry<String, Process> entry : uniqueProcesses.entrySet()) {
            quantums.add(entry.getValue().getRemainingQuantum());
        }
        quantumUpdate.add(quantums);
    }

    public void startProcess() {
        while (!(currProcess == null && readyQueue.isEmpty() && processes.isEmpty())) {
            if (currProcess == null) {
                if (readyQueue.isEmpty())
                    getNextReady();
                currProcess = readyQueue.get(0);
                readyQueue.remove(0);
            }
            uniqueProcesses.replace(currProcess.getProcessName(), currProcess);
            if (currProcess.getResponseTime() == -1)
                currProcess.setResponseTime(time);
            if (currProcess.getAG() == 1) {

                int quarterTime = (currProcess.getRemainingQuantum() + 3) / 4;
                int remainingTime = currProcess.getRemainingTime() - quarterTime;
                int remainingQuantum = currProcess.getRemainingQuantum() - quarterTime;
                if (remainingQuantum == 0) {
                    int newQuantum = currProcess.getRemainingQuantum() + 2;
                    currProcess.setRemainingQuantum(newQuantum);
                    currProcess.setAG(1);
                    readyQueue.add(currProcess);
                    currProcess = null;
                    continue;
                }
                if (remainingTime > 0) {
                    time += quarterTime;
                    currProcess.setRemainingTime(remainingTime);
                    currProcess.setRemainingQuantum(remainingQuantum);
                    updateReadyQueue();
                    Process highestPriority = getHighestPriority();
                    if (highestPriority.getProcessName().equals(currProcess.getProcessName())) {
                        currProcess.setAG(2);
                    } else {
                        currProcess.setRemainingQuantum((remainingQuantum + 1) / 2 + currProcess.getQuantum());
                        currProcess.setQuantum(currProcess.getRemainingQuantum());
                        calculateQuantumUpdate();
                        //System.out.println(currProcess.getProcessName());
                        currProcess.setMiddle(time);
                        Process finalProcess = new Process();
                        finalProcess.equals(currProcess);
                        finalProcesses.add(finalProcess);
                        currProcess.setAG(1);
                        readyQueue.add(currProcess);
                        currProcess = highestPriority;
                    }

                } else {

                    time += Math.min(quarterTime, currProcess.getRemainingTime());
                    updateReadyQueue();
                    currProcess.setRemainingTime(0);
                    currProcess.setRemainingQuantum(0);
                    currProcess.setCompletionTime(time);
                    calculateQuantumUpdate();
                    //System.out.println(currProcess.getProcessName());
                    currProcess.setMiddle(time);
                    Process finalProcess = new Process();
                    finalProcess.equals(currProcess);
                    finalProcesses.add(finalProcess);
                    currProcess = null;
                }

            } else if (currProcess.getAG() == 2) {
                int quarterTime = (currProcess.getRemainingQuantum() + 3) / 4;

                int remainingTime = currProcess.getRemainingTime() - quarterTime;
                int remainingQuantum = currProcess.getRemainingQuantum() - quarterTime;
                if (remainingQuantum == 0) {
                    int newQuantum = currProcess.getRemainingQuantum() + 2;
                    currProcess.setRemainingQuantum(newQuantum);
                    currProcess.setAG(1);
                    readyQueue.add(currProcess);
                    currProcess = null;
                    continue;
                }
                if (remainingTime > 0) {
                    time += quarterTime;
                    currProcess.setRemainingTime(remainingTime);
                    currProcess.setRemainingQuantum(remainingQuantum);
                    updateReadyQueue();
                    Process lessTime = getLessRemainingTime();
                    if (lessTime.getProcessName().equals(currProcess.getProcessName())) {
                        currProcess.setAG(3);
                    } else {
                        currProcess.setRemainingQuantum(remainingQuantum + currProcess.getQuantum());
                        currProcess.setQuantum(currProcess.getRemainingQuantum());
                        calculateQuantumUpdate();
                        //System.out.println(currProcess.getProcessName());
                        currProcess.setMiddle(time);
                        Process finalProcess = new Process();
                        finalProcess.equals(currProcess);
                        finalProcesses.add(finalProcess);
                        currProcess.setAG(1);
                        readyQueue.add(currProcess);
                        currProcess = lessTime;
                    }

                } else {

                    time += Math.min(quarterTime, currProcess.getRemainingTime());
                    updateReadyQueue();
                    currProcess.setRemainingTime(0);
                    currProcess.setRemainingQuantum(0);
                    currProcess.setCompletionTime(time);
                    calculateQuantumUpdate();
                    //System.out.println(currProcess.getProcessName());
                    currProcess.setMiddle(time);
                    Process finalProcess = new Process();
                    finalProcess.equals(currProcess);
                    finalProcesses.add(finalProcess);
                    currProcess = null;
                }


            } else if (currProcess.getAG() == 3) {
                int quarterTime = (currProcess.getRemainingQuantum() + 1) / 2;
                for (int i = 1; i <= quarterTime; i++) {

                    if (currProcess == null) {
                        if (readyQueue.isEmpty())
                            getNextReady();
                        currProcess = readyQueue.get(0);
                        readyQueue.remove(0);
                    }
                    if (currProcess.getResponseTime() == -1)
                        currProcess.setResponseTime(time);

                    int remainingTime = currProcess.getRemainingTime() - 1;
                    int remainingQuantum = currProcess.getRemainingQuantum() - 1;
                    if (remainingQuantum == 0) {
                        int newQuantum = currProcess.getRemainingQuantum() + 2;
                        currProcess.setRemainingQuantum(newQuantum);
                        currProcess.setAG(1);
                        readyQueue.add(currProcess);
                        currProcess = null;
                        continue;
                    }
                    if (remainingTime > 0) {
                        time++;
                        currProcess.setRemainingTime(remainingTime);
                        currProcess.setRemainingQuantum(remainingQuantum);
                        Process lessTime = getLessRemainingTime();
                        if (lessTime.getProcessName().equals(currProcess.getProcessName())) {
                            if (quarterTime == i)
                                currProcess.setAG(3);
                        } else {
                            currProcess.setRemainingQuantum(remainingQuantum + currProcess.getQuantum());
                            currProcess.setQuantum(currProcess.getRemainingQuantum());
                            calculateQuantumUpdate();
                            //System.out.println(currProcess.getProcessName());
                            currProcess.setMiddle(time);
                            Process finalProcess = new Process();
                            finalProcess.equals(currProcess);
                            finalProcesses.add(finalProcess);
                            currProcess.setAG(1);
                            readyQueue.add(currProcess);
                            currProcess = lessTime;
                        }

                    } else {

                        time++;
                        updateReadyQueue();
                        currProcess.setRemainingTime(0);
                        currProcess.setRemainingQuantum(0);
                        currProcess.setCompletionTime(time);
                        calculateQuantumUpdate();
                        //System.out.println(currProcess.getProcessName());
                        currProcess.setMiddle(time);
                        Process finalProcess = new Process();
                        finalProcess.equals(currProcess);
                        finalProcesses.add(finalProcess);
                        currProcess = null;
                        break;
                    }

                }
            }

        }

    }

    public void updateReadyQueue() {
        while (true) {
            int mx = time;
            int idx = -1;
            for (int i = 0; i < processes.size(); i++) {
                if (mx >= processes.get(i).getArrivalTime()) {
                    mx = processes.get(i).getArrivalTime();
                    idx = i;
                }
            }
            if (idx == -1)
                break;
            readyQueue.add(processes.get(idx));
            processes.remove(idx);
        }

    }

    public Process getHighestPriority() {
        if (readyQueue.size() == 0)
            return currProcess;
        Process highestPriority = currProcess;
        int idx = 0;
        int idx1 = -1;
        for (Process process : readyQueue) {
            if (process.getPriority() < highestPriority.getPriority()) {
                highestPriority = process;
                idx1 = idx;
            }
            idx++;
        }
        if (idx1 == -1)
            return currProcess;
        readyQueue.remove(idx1);
        return highestPriority;

    }

    public Process getLessRemainingTime() {
        if (readyQueue.size() == 0)
            return currProcess;
        Process lessTime = currProcess;
        int idx = 0;
        int idx1 = -1;
        for (Process process : readyQueue) {
            if (lessTime.getRemainingTime() > process.getRemainingTime()) {
                lessTime = process;
                idx1 = idx;
            }
            idx++;
        }
        if (idx1 == -1)
            return currProcess;
        readyQueue.remove(idx1);
        return lessTime;

    }

    public void show() {
        System.out.println("Quantum Update: ");
        for (int i = 0; i < quantumUpdate.size(); i++) {
            System.out.print("( ");
            for (int j = 0; j < quantumUpdate.get(i).size(); j++) {
                if (j < quantumUpdate.get(i).size() - 1)
                    System.out.print(quantumUpdate.get(i).get(j) + ", ");
                else
                    System.out.print(quantumUpdate.get(i).get(j));


            }
            System.out.println(" )");

        }
        System.out.println("--------------------------------------------------");
        System.out.println("Processes Order: ");
        for (int i = 0; i < finalProcesses.size(); i++) {
            System.out.println(finalProcesses.get(i).getProcessName() + "  " + finalProcesses.get(i).getMiddle());
        }
        System.out.println("--------------------------------------------------");
        double averageWaitingTime = 0;
        double averageTurnaround = 0;
        System.out.println("PName\tBurst\tArr\t  Priority Quantum \tWait  Turn \tCompletion");
        for (Map.Entry<String, Process> x : uniqueProcesses.entrySet()) {
            System.out.println(x.getValue().getProcessName() + "\t\t" +
                    x.getValue().getBurstTime() + "\t\t" +
                    x.getValue().getArrivalTime() + "\t\t" +
                    x.getValue().getPriority() + "\t\t" +
                    x.getValue().getQuantum() + "\t\t" +
                    x.getValue().getWaitingTime() + "\t\t" +
                    x.getValue().getTurnaroundTime() + "\t\t" +
                    x.getValue().getCompletionTime() + "\t\t"
            );
            averageWaitingTime += x.getValue().getWaitingTime();
            averageTurnaround += x.getValue().getTurnaroundTime();
        }
        System.out.println("-------------------------------------------------------");
        averageTurnaround /= uniqueProcesses.size();
        averageWaitingTime /= uniqueProcesses.size();
        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaround);


    }


}

