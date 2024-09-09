package MemorySimulator;
import MemorySimulator.Memory.Mem;
import MemorySimulator.Scheduler.*;
import MemorySimulator.Tasks.Task;
import MemorySimulator.Queue.*;
import java.util.Scanner;

import MemorySimulator.Tasks.TaskGenerator;
public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        Mem memory = new Mem();
        Task[] tareas;
        ListaLigada[] colas;

        System.out.println("¿Desea generar las tareas nuevamente?");
        if(input.next().equals("Y")){
            tareas = TaskGenerator.generateManyTasks(10);
            TaskGenerator.exportTasksToFile(tareas);
        }
        colas = TaskGenerator.importTasksFromFileToArray("tasks.txt");

        // Iniciamos el scheduler con las colas y la memoria
        scheduler sched = new scheduler(colas, memory);
        long startTime = System.currentTimeMillis();
        sched.start();
        sched.join();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (executionTime/1000) + " segundos");

    }
}