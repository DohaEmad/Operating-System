package com.company;

public class Partition {
    private String name;
    private Double space;
    private Boolean busy;
    private Boolean external;
    private String processName;
    private String from;


    public Partition(String name, Double space, Boolean busy) {
        this.name = name;
        this.space = space;
        this.busy = busy;
        this.external=false;
        this.processName="There is no process";
        this.from=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSpace() {
        return space;
    }

    public void setSpace(Double space) {
        this.space = space;
    }

    public Boolean isBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }

    public Boolean getBusy() {
        return busy;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    public void deepCopy(Partition temp){
        this.from=temp.getFrom();
        this.processName=temp.getProcessName();
        this.external=temp.getExternal();
        this.busy=temp.getBusy();
        this.space=temp.getSpace();
        this.name=temp.getName();
    }
    public void show(){
        System.out.println(getName()+" ( "+getSpace()+" ) => "+(isBusy()?getProcessName():"External Fragment"));
    }
}
