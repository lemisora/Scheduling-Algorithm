package MemorySimulator.Scheduler;
import MemorySimulator.Queue.ListaLigada;
import MemorySimulator.Queue.Nodo;
import MemorySimulator.Tasks.Task;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Sched2 implements Runnable {
    private ListaLigada lis;
    private String nombreLista;
    private int numProcesadores;

    public Sched2(ListaLigada lis, String nombreLista, int numProcesadores) {
        this.lis = lis;
        this.nombreLista = nombreLista;
        this.numProcesadores = numProcesadores;

    }

    @Override
    public void run() {
        Nodo referencia = lis.getHead();
        System.out.println("Nombre de la lista: " + nombreLista);
        System.out.println("Numero de procesadores: " + this.numProcesadores + "\n");

        // Crear un ExecutorService con un número fijo de hilos
        ExecutorService executor = Executors.newFixedThreadPool(this.numProcesadores);

        while (referencia != null) {
            Task task = referencia.getTask();

            // Enviar la tarea al ExecutorService
            executor.submit(() -> {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Proceso Enviado: " + task.getName() + 
                        " Tamano: " + task.getTamano() + 
                        " Tiempo: " + task.getTiempo() + 
                        " en el hilo: " + threadName + "\n ");
                    Thread.sleep(task.getTiempo() * 100);
                } catch (InterruptedException e) {
                    System.out.println(nombreLista + " fue interrumpida");
                }
                System.out.println("Proceso " + task.getName() + " de " + task.getTiempo() + " segundos. Ha terminado\n");
            });

            referencia = referencia.getSig();
        }

        // Cerrar el ExecutorService
        executor.shutdown();
        try {
            // Esperar a que todas las tareas terminen
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println(nombreLista + " ha terminado de imprimir");
    }
    
}
