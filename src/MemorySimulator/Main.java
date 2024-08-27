package MemorySimulator;
import MemorySimulator.Memory.*;
import MemorySimulator.Tasks.Task;
import MemorySimulator.Scheduler.sched;
public class Main {
    static Mem memoria;
    public static void main(String[] args) throws InterruptedException {
        memoria = new Mem();
        sched despacho = new sched();
        despacho.join(10000);
//        if(memoria.partition_allocate_process((short) 2, tar1.getName(), (short)tar1.getTamano()) != true)
//            System.out.println("No se pudo alojar la tarea en la partición 1");
//        else
//            System.out.println("Alojada correctamente");
//
//        if(memoria.partition_allocate_process((short) 2, tar1.getName(), (short)tar1.getTamano()) != true)
//            System.out.println("No se pudo alojar la tarea en la partición 1");
//        else
//            System.out.println("Alojada correctamente");
    }
}