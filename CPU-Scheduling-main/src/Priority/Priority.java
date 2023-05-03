package Priority;

import Process.PriorityCompare;
import Process.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Priority {
    int sumWaiting = 0, sumTurnround = 0;
    private ArrayList<Process> processes = new ArrayList<Process>();
    private int time;
    private int context;
    private ArrayList<Process> processOrder = new ArrayList<>();
    private ArrayList<Process> readyQueue = new ArrayList<>();
    private HashMap<String, Process> uniqueProcesses = new HashMap<String, Process>();
    private Process currProcess;
    private int starvation;

    public Priority(ArrayList<Process> pros, int priorityCounter) {
        this.processes = pros;
        this.time = 0;
        this.currProcess = null;
        this.starvation = priorityCounter;

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


    public void startProcess() {
        int firstProcess = 0;
        while (!readyQueue.isEmpty() || !processes.isEmpty()) {
            updateReadyQueue();
            if (readyQueue.isEmpty())
                getNextReady();
            Collections.sort(readyQueue, new PriorityCompare());
            Process newProcess = readyQueue.get(0);
            if (currProcess == null)
                currProcess = newProcess;
            if (!currProcess.getProcessName().equals(newProcess.getProcessName())) {
                currProcess.setMiddle(time);
                // time += context;
                Process tempProcess = new Process();
                tempProcess.equals(currProcess);
                processOrder.add(tempProcess);

                //System.out.println(currProcess.getProcessName());

            }

            currProcess = newProcess;
            currProcess.setCounter(0);

            readyQueue.remove(0);
            int remainingTime = currProcess.getRemainingTime() - 1;
            currProcess.setRemainingTime(remainingTime);
            time++;
            for (int i = 0; i < readyQueue.size(); i++) {
                int c = readyQueue.get(i).getCounter();
                c++;
                readyQueue.get(i).setCounter(c);
                if (readyQueue.get(i).getCounter() >= starvation) {
                    int p = readyQueue.get(i).getPriority();
                    int newP = Math.max(0, p - 1);
                    readyQueue.get(i).setPriority(newP);
                }
            }
            if (currProcess.getRemainingTime() != 0) {
                readyQueue.add(currProcess);
            } else {
                //time+=context;
                if (firstProcess == 0) {
                    // time += context;
                    firstProcess++;
                }
                currProcess.setCompletionTime(time);
                if (readyQueue.isEmpty() && processes.isEmpty()) {
                    //System.out.println(currProcess.getProcessName());
                    currProcess.setMiddle(time);
                    Process tempProcess = new Process();
                    tempProcess.equals(currProcess);
                    processOrder.add(tempProcess);
                }

            }

        }

    }

    public void show() {
        int n = processOrder.size();
        System.out.println("The order of processes: ");
        for (int i = 0; i < n; i++) {
            System.out.println(processOrder.get(i).getProcessName() + "  " + processOrder.get(i).getMiddle());
            uniqueProcesses.put(processOrder.get(i).getProcessName(), processOrder.get(i));
        }
        System.out.println();
        System.out.println("---------------------------------------------------------");
        System.out.println("P\t\t" + "AT\t\t" + "WT\t\t" + "TAT\t\t" + "CT\t\t" + "Priority\t\t");
        for (Map.Entry<String, Process> process : uniqueProcesses.entrySet()) {
            sumWaiting += process.getValue().getWaitingTime();
            sumTurnround += process.getValue().getTurnaroundTime();
            System.out.println(process.getValue().getProcessName() + "\t\t"
                    + process.getValue().getArrivalTime() + "\t\t"
                    + process.getValue().getWaitingTime() + "\t\t"
                    + process.getValue().getTurnaroundTime() + "\t\t"
                    + process.getValue().getCompletionTime() + "\t\t"
                    + process.getValue().getPriority() + "\t\t"
            );
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("average waiting time is: " + ((double) sumWaiting / (double) uniqueProcesses.size()));
        System.out.println("average turnround time is: " + ((double) sumTurnround / (double) uniqueProcesses.size()));
    }
}
