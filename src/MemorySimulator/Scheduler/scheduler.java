package MemorySimulator.Scheduler;
import MemorySimulator.Memory.Mem;
import MemorySimulator.Queue.*;
import MemorySimulator.Tasks.Task;

public class scheduler extends Thread {
    private final ListaLigada[] colas;
    private final Mem memory_to_use;
    private boolean running = true;
    public scheduler(ListaLigada[] queue, Mem memory) {
        this.colas = queue;
        this.memory_to_use = memory;
    }

    //Clase interna para procesar tareas
    private class ProcesadorTareas extends Thread{
        private final ListaLigada cola;
        private final int part;

        public ProcesadorTareas(ListaLigada queue, int part) {
            this.cola = queue;
            this.part = part;
        }

        @Override
        public void run(){
            while (running){
                Nodo nodoActual = cola.pull_node();
                if(nodoActual != null){
                    Task tarea = nodoActual.getTask();
                    if(memory_to_use.partition_allocate_process(part, tarea.getName(), tarea.getTamano() ) ){
                        System.out.println("Tarea "+tarea.getName()+" asignada a partición "+part );
                        try {
                            Thread.sleep(tarea.getTiempo()* 1000L);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        memory_to_use.partition_deallocate_process(part);
                        System.out.println("Tarea "+tarea.getName()+" ha finalizado");
                    } else {
                        System.out.println("Tarea "+tarea.getName()+" no cabe en la partición "+part );
                    }
                }else{
                    running = false;
                }
            }
        }
    }
    //Fin clase interna

    @Override
    public void run(){
        for (int i = 0; i < colas.length; i++) {
            new ProcesadorTareas(colas[i], i).start();
        }
    }
}
