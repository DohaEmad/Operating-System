package com.company;

public class Process {
    private String name;
    private Double space;
    private String partitionName;
    public Process(String name, Double space) {
        this.name = name;
        this.space = space;
        partitionName="";
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

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }
    public void show(){
        System.out.println(getName()+"  can not be allocated");
    }
}
