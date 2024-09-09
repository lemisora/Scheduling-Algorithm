package MemorySimulator.Scheduler;
import MemorySimulator.Memory.Mem;
import MemorySimulator.Queue.*;
import MemorySimulator.Tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class scheduler extends Thread {
    private final ListaLigada[] colas;
    private final Mem memory_to_use;
    private boolean[] colaVacia;
    public int completadas = 0;
    public int descartadas = 0;
    private Thread[] hilos;

    public scheduler(ListaLigada[] queue, Mem memory) {
        this.colas = queue;
        this.memory_to_use = memory;
        this.hilos = new Thread[queue.length];
        this.colaVacia = new boolean[queue.length];
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
            while (true){
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
                    break;
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

    public void reOrdenaCola(){
        ArrayList <Integer> Indices = new ArrayList<>();
        for (int i = 0; i < colas.length; i++){
            colaVacia[i] = colas[i].estaVacia();
            if(colaVacia[i])
                Indices.add(i);
        }
        if(!Indices.isEmpty())
            System.out.println("Las colas vacías son las siguientes "+ Arrays.toString(Indices.toArray()));
    }
}
