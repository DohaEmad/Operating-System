package RR;

import java.util.*;

import Process.Process;
import Process.ArrivalTimeCompare;

public class RR {

    private ArrayList<Process> processes = new ArrayList<>();
    private int contextSwitching;
    private int time;
    private int quantum;
    private double avaTurnAround;
    private double avaWaitingTime;


    public RR(ArrayList<Process> processes, int contextSwitching, int quantum) {
        this.processes = processes;
        this.contextSwitching = contextSwitching;
        this.time = 0;
        this.quantum = quantum;
        this.avaTurnAround = 0;
        this.avaWaitingTime = 0;
        Collections.sort(processes, new ArrivalTimeCompare());
    }

    public void startProcess() {
        System.out.println("Processes execution order: ");
        while (true) {
            boolean ok = true;
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).getCompletionTime() == -1) {
                    ok = false;
                    break;

                }
            }
            if (ok) break;

            int f = 0;
            int notDone = 0;
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).getCompletionTime() == -1)
                    notDone++;
                if (processes.get(i).getArrivalTime() > time) {
                    f++;
                    continue;
                }

                if (processes.get(i).getCompletionTime() == -1) {
                    if (processes.get(i).getRemainingTime() <= quantum) {
                        time = time + (processes.get(i).getRemainingTime() + contextSwitching);
                        processes.get(i).setRemainingTime(0);
                        processes.get(i).setCompletionTime(time);
                    } else {
                        processes.get(i).setRemainingTime(processes.get(i).getRemainingTime() - quantum);
                        time = time + (quantum + contextSwitching);
                    }
                    System.out.println(processes.get(i).getProcessName() + " " + time);
                    if (processes.get(i).getCompletionTime() != -1) {
                        processes.get(i).setTurnaroundTime(time - processes.get(i).getArrivalTime());
                        processes.get(i).setWaitingTime(time - processes.get(i).getBurstTime() - processes.get(i).getArrivalTime());
                        avaTurnAround += processes.get(i).getTurnaroundTime();
                        avaWaitingTime += processes.get(i).getWaitingTime();
                        break;
                    }
                }

            }
            if (notDone == f)
                time++;
        }

    }

    public void show() {
        System.out.println("------------------------------------------------------");
        System.out.println("Process Name\tWaiting Time\tTurn Around Time");
        for (int i = 0; i < processes.size(); i++) {
            System.out.println(processes.get(i).getProcessName() + "\t\t\t\t" + processes.get(i).getWaitingTime() + "\t\t\t\t" + processes.get(i).getTurnaroundTime());
        }
        avaWaitingTime /= processes.size();
        avaTurnAround /= processes.size();
        System.out.println("------------------------------------------------------");
        System.out.println("Average Waiting Time is: " + avaWaitingTime);
        System.out.println("Average Turnaround Time is: " + avaTurnAround);
    }

}

