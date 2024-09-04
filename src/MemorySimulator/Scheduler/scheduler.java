package MemorySimulator.Scheduler;
import MemorySimulator.Memory.Mem;
import MemorySimulator.Queue.*;
import MemorySimulator.Tasks.Task;

public class scheduler extends Thread {
    private final ListaLigada[] colas;
    private final Mem memory_to_use;
    public int completadas = 0;
    public int descartadas = 0;
    private boolean running = true;
    private Thread[] hilos;

    public scheduler(ListaLigada[] queue, Mem memory) {
        this.colas = queue;
        this.memory_to_use = memory;
        this.hilos = new Thread[queue.length];
        System.out.println("\n------Comienzo de Scheduler------");
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
                        System.out.println(tarea.getName()+" asignada a partición "+part );
                        try {
                            Thread.sleep(tarea.getTiempo()* 1000L);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        memory_to_use.partition_deallocate_process(part);
                        System.out.println(tarea.getName()+" ha finalizado");
                        completadas++;
                    } else {
                        System.out.println(tarea.getName()+" no cabe en la partición "+part );
                        descartadas++;
                    }
                }else{
                    running = false;
//                    System.out.println("Tareas finalizadas: "+ completadas+"\nTareas descartadas: "+descartadas+"\n");
                }
            }
        }
    }
    //Fin clase interna

    @Override
    public void run(){
        for (int i = 0; i < colas.length; i++) {
            hilos[i] = new ProcesadorTareas(colas[i], i);
            hilos[i].start();
        }

        for(Thread hilo : hilos){
            try{
                hilo.join();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\nTareas finalizadas: "+ completadas+"\nTareas descartadas: "+descartadas+"\n");
    }
}
