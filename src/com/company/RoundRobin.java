package com.company;

import java.util.ArrayList;

public class RoundRobin extends Scheduler implements IScheduler {

    private Task previousTask;

    @Override
    public void addTask(Task task) {
        waitingTasks.add(task);
    }

    @Override
    public void waitAll() {
        for (Task task : tasks) {
            task.setWaitingTime(task.getWaitingTime() + 1);
        }
    }


    @Override
    public boolean hasWaitingTask() {
        return !waitingTasks.isEmpty();
    }

    @Override
    public void remove(Task task) {
        this.tasks.remove(task);
    }

    public void tick(ShortestJobFirst sjfScheduler) {
        if (!tasks.isEmpty()) {
            Task currentTask = tasks.get(0);
            //Running second time
            if (currentTask == previousTask) {
                tasks.remove(currentTask);
                currentTask.run(this);
                for (Task task : tasks) {
                    task.setWaitingTime(task.getWaitingTime() + 1);
                }
                if (currentTask.getRemainingTime() > 0) {
                    tasks.add(currentTask);
                }
            }
            //Running first time
            else {
                currentTask.run(this);
                for (Task task : tasks) {
                    if (task != currentTask) {
                        task.setWaitingTime(task.getWaitingTime() + 1);
                    }
                }
            }
            previousTask = currentTask;
        }
    }


}
