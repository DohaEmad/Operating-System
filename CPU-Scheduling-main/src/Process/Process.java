package Process;

public class Process {

    private String processName;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int completionTime;
    private int turnaroundTime;
    private int waitingTime;
    private int responseTime;
    private int quantum;
    private int remainingTime;
    private int AG = 0;
    private int remainingQuantum;
    private int middle;
    private int counter;

    public Process() {

    }

    public Process(String processName, int arrivalTime, int burstTime) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.responseTime = -1;
        this.remainingQuantum = quantum;
        this.completionTime = -1;
        this.counter=0;

    }

    public Process(String processName, int arrivalTime, int burstTime, int priority) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.responseTime = -1;
        this.remainingQuantum = quantum;
        this.completionTime = -1;
        this.counter=0;
    }

    public Process(String processName, int arrivalTime, int burstTime, int priority, int quantum) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.quantum = quantum;
        this.remainingTime = burstTime;
        this.responseTime = -1;
        this.remainingQuantum = quantum;
        this.completionTime = -1;
        this.counter=0;

    }

    public Process(String processName, int arrivalTime, int burstTime, int priority, int quantum, int AG) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.quantum = quantum;
        this.AG = AG;
        this.remainingTime = burstTime;
        this.responseTime = -1;
        this.remainingQuantum = quantum;
        this.completionTime = -1;
        this.counter=0;


    }

    public void equals(Process process) {
        this.processName = process.getProcessName();
        this.arrivalTime = process.getArrivalTime();
        this.burstTime = process.getBurstTime();
        this.priority = process.getPriority();
        this.completionTime = process.getCompletionTime();
        this.turnaroundTime = process.getTurnaroundTime();
        this.waitingTime = process.getWaitingTime();
        this.responseTime = process.getResponseTime();
        this.quantum = process.getQuantum();
        this.remainingTime = process.getRemainingTime();
        this.AG = process.getAG();
        this.remainingQuantum = process.getRemainingQuantum();
        this.middle = process.getMiddle();
        this.counter=process.getCounter();
    }


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getMiddle() {
        return middle;
    }

    public void setMiddle(int middle) {
        this.middle = middle;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
        int waiting = completionTime - arrivalTime - burstTime;
        setWaitingTime(waiting);
        int turnaround = getWaitingTime() + getBurstTime();
        setTurnaroundTime(turnaround);
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getAG() {
        return AG;
    }

    public void setAG(int AG) {
        this.AG = AG;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getRemainingQuantum() {
        return remainingQuantum;
    }

    public void setRemainingQuantum(int remainingQuantum) {
        this.remainingQuantum = remainingQuantum;
    }

    public void showProcess() {
        System.out.println("-----------------------------------------");
        System.out.println("Name: " + processName);
        System.out.println("BurstTime: " + burstTime);
        System.out.println("ArrivalTime: " + arrivalTime);
        System.out.println("Priority: " + priority);
        System.out.println("CompletionTime: " + completionTime);
        System.out.println("WaitingTime: " + waitingTime);
        System.out.println("TurnaroundTime: " + turnaroundTime);
        System.out.println("ResponseTime: " + responseTime);
        System.out.println("RemainingQuantum: " + remainingQuantum);
        System.out.println("-----------------------------------------");

    }

}
