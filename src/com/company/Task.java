package com.company;

import static com.company.Main.arrivals;
import static com.company.Main.taskNames;

public class Task {
    private String name;
    private int priority;
    private int startTime;
    private int length;

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    private int remainingTime;

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    private int waitingTime;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Task(String name, int priority, int startTime, int length) {
        this.name = name;
        this.priority = priority;
        this.startTime = startTime;
        this.length = length;
        this.remainingTime = length;
        this.waitingTime = 0;
    }

    public void run(IScheduler scheduler){
//        System.out.println("Now running: " + this.name);
        this.remainingTime--;
        if(this.remainingTime <= 0){
            scheduler.remove(this);
            for(Task task: arrivals){
                if(task.getName().equals(this.name)){
                    task.setWaitingTime(this.waitingTime);
                }
            }
        }

        if(this.name.charAt(0) != taskNames.charAt(taskNames.length()-1)){
            taskNames += this.name;
        }
    }


}
