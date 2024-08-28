package MemorySimulator.Scheduler;
import MemorySimulator.Tasks.TaskGenerator;
public class Pruebas {
    
    public static void main(String[] args) {
        TaskGenerator clastask =new TaskGenerator();

        clastask.generateManyTasks(20);
        TaskGenerator.exportTasksToFile(clastask.generateManyTasks(20));
        //clastask.importTasksFromFile(tasks.txt);

        Sched2 imprimeLista = new Sched2(clastask.importTasksFromFile("tasks.txt"),"Lista1");

        Thread hilo1 =new Thread(imprimeLista);
        Thread hilo2 =new Thread(imprimeLista);
        Thread hilo3 =new Thread(imprimeLista);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            //hilo4.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido.");
        }


    }
}
