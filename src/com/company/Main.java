package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {

    public static String taskNames;
    public static int currentTime = 0;
    public static ArrayList<Task> arrivals = new ArrayList<>();


    public static void main(String[] args) {
        taskNames = " ";
        Scanner scanner = new Scanner(System.in);
        ShortestJobFirst sjfScheduler = new ShortestJobFirst();
        RoundRobin rrScheduler = new RoundRobin();
        String nextLine;

        //Read input until EOF found (empty line)
        while (scanner.hasNextLine() && !(nextLine = scanner.nextLine()).equals("")) {
            String[] stringToTask = nextLine.split(",");
            Task newTask = new Task(stringToTask[0], Integer.parseInt(stringToTask[1]), Integer.parseInt(stringToTask[2]), Integer.parseInt(stringToTask[3]));
            arrivals.add(newTask);
            if (newTask.getPriority() == 0) {
                rrScheduler.addTask(newTask);
            } else {
                sjfScheduler.addTask(newTask);
            }
        }

        //Start scheduling
        while (sjfScheduler.hasWaitingTask() || rrScheduler.hasWaitingTask() || sjfScheduler.hasRemainingTask() || rrScheduler.hasRemainingTask()) {
            sjfScheduler.refreshTasks();
            rrScheduler.refreshTasks();
            if (sjfScheduler.hasRemainingTask()) {
                sjfScheduler.tick(rrScheduler);
                rrScheduler.waitAll();
            } else if (rrScheduler.hasRemainingTask()) {
                rrScheduler.tick(sjfScheduler);
            }
            currentTime++;
        }
        System.out.println(taskNames.trim());
        arrivals.sort(Comparator.comparing(Task::getStartTime));
        for(int i = 0; i < arrivals.size()-1; i++){
            System.out.print(arrivals.get(i).getName() + ":" + arrivals.get(i).getWaitingTime() + ",");

        }
            System.out.print(arrivals.get(arrivals.size()-1).getName() + ":" + arrivals.get(arrivals.size()-1).getWaitingTime());
    }
}
