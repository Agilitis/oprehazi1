package com.company;

public interface IScheduler {

    public void addTask(Task task);
    public void waitAll();
    public void refreshTasks();
    public boolean hasWaitingTask();
    void remove(Task task);
}
