package MemorySimulator.Scheduler;
import MemorySimulator.Queue.ListaLigada;
import MemorySimulator.Queue.Nodo;
import MemorySimulator.Tasks.Task;

public class Sched2 implements Runnable {
    private ListaLigada lis;
    private String nombreLista;


    public Sched2(ListaLigada lis, String nombreLista){
        this.lis = lis;
        this.nombreLista = nombreLista;
    }

    @Override
    public void run(){
        Nodo referencia = lis.getHead();
        System.out.println("Nombre de la lista: "+nombreLista );
        while(referencia != null){
            Task task =referencia.getTask();
            System.out.println("Proceso actual: "+task.getName()+" Tamano: "+task.getTamano()+" Tiempo: "+task.getTiempo() + "\n ");
            try{
                Thread.sleep(task.getTiempo()*100);
            }catch(InterruptedException e){
                System.out.println(nombreLista + "fue interrumpida");
            }
            referencia = referencia.getSig();
        }
        //System.out.println(nombreLista + " ha terminado de imprimir");
    }
    
}
