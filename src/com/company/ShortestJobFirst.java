package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class ShortestJobFirst extends Scheduler implements IScheduler {


    public void tick(RoundRobin rrScheduler) {
        Task shortestTask = tasks.get(0);
        shortestTask.run(this);
        for (Task task : tasks) {
            if (task != shortestTask) {
                task.setWaitingTime(task.getWaitingTime() + 1);
            }
        }
    }

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
        tasks.sort(Comparator.comparing(Task::getLength));
    }
}
