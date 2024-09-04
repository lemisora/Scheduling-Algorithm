package MemorySimulator;
import MemorySimulator.Memory.Mem;
import MemorySimulator.Scheduler.*;
import MemorySimulator.Tasks.Task;
import MemorySimulator.Queue.*;

import MemorySimulator.Tasks.TaskGenerator;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Mem memory = new Mem();
        // Creamos 4 colas para las 4 particiones
        ListaLigada[] colas = new ListaLigada[memory.MemoryParts()];
        for (int i = 0; i < colas.length; i++) {
            colas[i] = new ListaLigada();
        }

        // Creamos algunas tareas de ejemplo y las distribuimos entre las colas
        Task tarea1 = new Task(" 1",100, 5);
        Task tarea2 = new Task(" 2",200, 3);
        Task tarea3 = new Task(" 3",300, 7);
        Task tarea4 = new Task(" 4",50, 2);

        colas[0].agregarNodo(tarea1);  // Asignada a la partición 1
        colas[1].agregarNodo(tarea2);  // Asignada a la partición 2
        colas[2].agregarNodo(tarea3);  // Asignada a la partición 3
        colas[3].agregarNodo(tarea4);  // Asignada a la partición 4

        // Iniciamos el scheduler con las colas y la memoria
        scheduler sched = new scheduler(colas, memory);
        long startTime = System.currentTimeMillis();
        sched.start();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " milliseconds");

    }
}