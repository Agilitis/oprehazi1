package com.company;

import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler implements IScheduler{
    protected List<Task> tasks = new ArrayList<Task>();
    protected List<Task> waitingTasks = new ArrayList<>();

    boolean hasRemainingTask(){
        return !tasks.isEmpty();
    }

    @Override
    public void refreshTasks() {
        ArrayList<Task> tasksToAdd = new ArrayList<>();
        for (Task task : waitingTasks) {
            if (task.getStartTime() <= Main.currentTime) {
                tasksToAdd.add(task);
                tasks.add(task);
            }
        }
        waitingTasks.removeAll(tasksToAdd);
    }

}
