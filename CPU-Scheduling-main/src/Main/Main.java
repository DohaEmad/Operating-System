package Main;

import AG.AG;
import Priority.Priority;
import Process.Process;
import RR.RR;
import SJF.SJF;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {


    static Scanner input = new Scanner(System.in);

    public static void RRTest() {
        ArrayList<Process> processes = new ArrayList<Process>();
        System.out.print("Enter Number Of Process: ");
        int n = input.nextInt();
        System.out.print("Enter Round robin Time Quantum: ");
        int q = input.nextInt();
        System.out.print("Enter Context switching: ");
        int context = input.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("For P" + (i + 1));

            System.out.print("Enter process Name: ");
            String processName = input.next();

            System.out.print("Enter Burst Time: ");
            int burstTime = input.nextInt();

            System.out.print("Enter Arrival Time: ");
            int arrivalTime = input.nextInt();

            System.out.print("Enter Priority: ");
            int priority = input.nextInt();


            Process process = new Process(processName, arrivalTime, burstTime,priority);
            processes.add(process);
        }
        RR rr = new RR(processes, context, q);
        rr.startProcess();
        rr.show();


        /*
RR


5
3
1
P1 4 0 0
P2 8 1 0
P3 2 3 0
P4 6 10 0
P5 5 12 0

5
3
1
P4 6 10 0
P1 4 0 0
P3 2 3 0
P5 5 12 0
P2 8 1 0



5
3
1
P1 4 0 0
P2 8 16 0
P3 2 3 0
P4 6 10 0
P5 5 12 0


*/

    }

    public static void AGTest() {
        ArrayList<Process> processes = new ArrayList<Process>();
        System.out.print("Enter Number Of Process: ");
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("For P" + (i + 1));

            System.out.print("Enter process Name: ");
            String processName = input.next();

            System.out.print("Enter Burst Time: ");
            int burstTime = input.nextInt();

            System.out.print("Enter Arrival Time: ");
            int arrivalTime = input.nextInt();


            System.out.print("Enter Priority: ");
            int priority = input.nextInt();

            System.out.print("Enter Quantum Time: ");
            int quantumTime = input.nextInt();

            Process process = new Process(processName, arrivalTime, burstTime, priority, quantumTime, 1);
            processes.add(process);

        }
        AG ag = new AG(processes);
        ag.startProcess();
        ag.show();
        /*
AG

4
P1 17 0 4 7
P2 6 2 7 9
P3 11 5 3 4
P4 4 15 6 6

*/
    }

    public static void SJFTest(){
        ArrayList<Process> processes = new ArrayList<Process>();
        System.out.print("Enter Number Of Process: ");
        int n = input.nextInt();
        System.out.print("Enter Context switching: ");
        int context = input.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("For P" + (i + 1));

            System.out.print("Enter process Name: ");
            String processName = input.next();

            System.out.print("Enter Burst Time: ");
            int burstTime = input.nextInt();

            System.out.print("Enter Arrival Time: ");
            int arrivalTime = input.nextInt();

            System.out.print("Enter Priority: ");
            int priority = input.nextInt();


            Process process = new Process(processName, arrivalTime, burstTime,priority);
            processes.add(process);
        }
        SJF sjf = new SJF(processes, context);
        sjf.startProcess();
        sjf.show();

        /*

SJF

4
1
P1 1 0 0
P2 7 2 0
P3 5 3 0
P4 8 5 0


7
1
P1 1 0 0
P2 7 1 0
P3 3 2 0
P4 6 3 0
P5 5 4 0
P6 15 5 0
P7 8 15 0


*/

    }

    public static void PriorityTest(){
        ArrayList<Process> processes = new ArrayList<Process>();
        System.out.print("Enter Number Of Process: ");
        int n = input.nextInt();
        System.out.print("Enter Starvation: ");
        int starvation = input.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("For P" + (i + 1));

            System.out.print("Enter process Name: ");
            String processName = input.next();

            System.out.print("Enter Burst Time: ");
            int burstTime = input.nextInt();

            System.out.print("Enter Arrival Time: ");
            int arrivalTime = input.nextInt();

            System.out.print("Enter Priority: ");
            int priority = input.nextInt();


            Process process = new Process(processName, arrivalTime, burstTime,priority);
            processes.add(process);
        }
       Priority priority=new Priority(processes,starvation);
        priority.startProcess();
        priority.show();

        /*
        Priority
5
7
P1 4 0 1
P2 3 0 2
P3 7 6 1
P4 4 11 3
P5 2 12 2


        */
    }



    public static void main(String[] args) {
            //AGTest();
            //RRTest();
            //SJFTest();
            PriorityTest();
    }
}
