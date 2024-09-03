package MemorySimulator.Scheduler;
import MemorySimulator.Memory.Mem;
import MemorySimulator.Queue.ListaLigada;
import MemorySimulator.Queue.Nodo;
import MemorySimulator.Tasks.Task;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class scheduler extends Thread {
    private final ListaLigada lista_ligada;
    private final String nombreLista;
    private final int numProcesadores;
    private final Mem memory_to_use;

    public scheduler(ListaLigada lis, String nombreLista, int numProcesadores, Mem memory) {
        this.lista_ligada = lis;
        this.nombreLista = nombreLista;
        this.numProcesadores = numProcesadores;
        this.memory_to_use = memory;
    }



//    @Override
//    public void run() {
//
//        System.out.println("Nombre de la lista: " + nombreLista);
//        System.out.println("Numero de procesadores: " + this.numProcesadores + "\n");
//        System.out.println("Particiones: 1-" + memory_to_use.prim_part +
//            " 2-" + memory_to_use.seg_part +
//            " 3-" + memory_to_use.ter_part +
//            " 4-"+ memory_to_use.cuarta_part+ "\n");
//
//        // Crear un ExecutorService con un número fijo de hilos
//        ExecutorService executor = Executors.newFixedThreadPool(this.numProcesadores);
//
//        int counter = 0;
//        while (!lista_ligada.estaVacia() || counter < lista_ligada.getSize()) {
//
//            // PRIMERO EN ENTRAR A LA COLA, PRIMERO EN SALIR
//            executor.submit(() -> {
//                Nodo nodo = lista_ligada.pull_node();
//                Task task = nodo.getTask();
//
//                boolean allocated = false;
//
//                try {
//                    // String threadName = Thread.currentThread().getName();
//                    for (int i = 0; i < memory_to_use.MemoryParts(); i++) {
//                        if (memory_to_use.fits_in_partition(i, task.getTamano())) {
//
//                            while (!memory_to_use.partition_is_free(i)) {
//                                //"Waiting partition to be free"
//                            }
//                            memory_to_use.partition_allocate_process(i, task.getName(), task.getTamano());
//                            System.out.println("Proceso " + task.getName() + " de " + task.getTamano() + " bytes. Ha sido asignado a la particion " + (i+1) + "\n");
//                            allocated = true;
//                            Thread.sleep(task.getTiempo() * 100);
//                            memory_to_use.partition_deallocate_process(i);
//                            break;
//                        }
//                    }
//
//                    if (!allocated){
//                        System.out.println("Proceso " + task.getName() + " de " + task.getTamano() + " bytes. No se pudo asignar\n");
//                    }
//
//                } catch (InterruptedException e) {
//                    System.out.println(nombreLista + " fue interrumpida");
//                }
//                if (allocated) {
//                    System.out.println("Proceso " + task.getName() + " de " + task.getTiempo() + " segundos. Ha terminado\n");
//                }
//
//            });
//
//        }
//
//        // Cerrar el ExecutorService
//        executor.shutdown();
//        try {
//            // Esperar a que todas las tareas terminen
//            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
//                executor.shutdownNow();
//
//            }
//        } catch (InterruptedException e) {
//            executor.shutdownNow();
//        }
//
//        System.out.println(nombreLista + " ha terminado de imprimir");
//    }
    
}
