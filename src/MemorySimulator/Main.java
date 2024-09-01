package MemorySimulator;
import MemorySimulator.Scheduler.Sched2;
import MemorySimulator.Tasks.TaskGenerator;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //TaskGenerator.exportTasksToFile(new TaskGenerator().generateManyTasks(20));
        
        // Obtener el número de procesadores disponibles de la computadora
        // puede ser necesario poner un numero menor si se quiere probar con menos procesadores
        int numProcesadores = (Runtime.getRuntime().availableProcessors());

        Sched2 sched2 = new Sched2(
            TaskGenerator.importTasksFromFile("tasks.txt"),
            "Lista Default",
            numProcesadores);
        long startTime = System.currentTimeMillis();
        sched2.run();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}