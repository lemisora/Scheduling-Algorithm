package MemorySimulator;
import MemorySimulator.Memory.*;
import MemorySimulator.Tasks.Task;
import MemorySimulator.Tasks.TaskGenerator;
import MemorySimulator.Scheduler.Sched2;
import MemorySimulator.Scheduler.sched;
public class Main {
    static Mem memoria;
    public static void main(String[] args) throws InterruptedException {
        //TaskGenerator.exportTasksToFile(new TaskGenerator().generateManyTasks(20));
        
        Sched2 sched2 = new Sched2(TaskGenerator.importTasksFromFile("tasks.txt"),"Lista Default");
        long startTime = System.currentTimeMillis();
        sched2.run();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}