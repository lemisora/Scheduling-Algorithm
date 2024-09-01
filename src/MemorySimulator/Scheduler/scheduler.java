package MemorySimulator.Scheduler;
import MemorySimulator.Memory.Mem;
import MemorySimulator.Queue.*;
import MemorySimulator.Tasks.Task;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class scheduler implements Runnable {
    private final ListaLigada lista_ligada;
    private final String nombreLista;
    private final int numProcesadores;
    private final Mem memory_to_use;
    private int counter = 0;

    public scheduler(ListaLigada lis, String nombreLista, int numProcesadores, Mem memory) {
        this.lista_ligada = lis;
        this.nombreLista = nombreLista;
        this.numProcesadores = numProcesadores;
        this.memory_to_use = memory;
    }

    @Override
    public void run() {

        System.out.println("Nombre de la lista: " + nombreLista);
        System.out.println("Numero de procesadores: " + this.numProcesadores + "\n");
        /* System.out.println("Particiones: 1-" + memory_to_use.prim_part + 
            " 2-" + memory_to_use.seg_part + 
            " 3-" + memory_to_use.ter_part + 
            " 4-"+ memory_to_use.cuarta_part+ "\n");
 */
        // Crear un ExecutorService con un número fijo de hilos
        ExecutorService executor = Executors.newFixedThreadPool(this.numProcesadores);
        // System.out.println(lista_ligada.getSize());
        
        while (!lista_ligada.estaVacia()) {
            // PRIMERO EN ENTRAR A LA COLA, PRIMERO EN SALIR
            executor.submit(() -> {
                Nodo nodo = lista_ligada.pull_node();
                Task task = nodo.getTask();
        
                boolean allocated = false;
        
                try {
                    int i = 0;
                    while (i < memory_to_use.MemoryParts() && !allocated) {
                        if (memory_to_use.fits_in_partition(i, task.getTamano())) {
                            int retryCount = 0;
                            while (!memory_to_use.partition_is_free(i) && retryCount < 10) {
                                // Esperar un poco antes de volver a intentar
                                TimeUnit.SECONDS.sleep(1);
                                retryCount++;
                            }
                            if (memory_to_use.partition_is_free(i)) {
                                // Asignar la partición aquí
                                allocated = true;
                                memory_to_use.partition_allocate_process(i, task.getName(), task.getTamano());
                                System.out.println("Proceso " + task.getName() + " asignado a la partición " + (i + 1));
                                Thread.sleep(task.getTiempo()*100);
                                memory_to_use.partition_deallocate_process(i);
                                System.out.println("Proceso " + task.getName() + " de " + task.getTiempo() + " segundos. Ha terminado\n");
                            } else {
                                System.out.println("Timeout esperando que la partición se libere");
                            }
                        }
                        i++;
                        if (i>=memory_to_use.MemoryParts()) {
                            System.out.println("Proceso " + task.getName() + " no cabe en ninguna partición de tamano: "+ task.getTamano());
                            System.out.println("No hay particiones disponibles para el proceso " + task.getName());
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println(nombreLista + " fue interrumpida");
                }
            });
        }

        // Cerrar el ExecutorService
        executor.shutdown();
        try {
            // Esperar a que todas las tareas terminen
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("Se termino el tiempo de espera");
                executor.shutdownNow();

            }
        } catch (InterruptedException e) {
            System.err.println("Interrupcion");
            executor.shutdownNow();
        }

        System.out.println(nombreLista + " ha terminado de imprimir");
    }
    
}
