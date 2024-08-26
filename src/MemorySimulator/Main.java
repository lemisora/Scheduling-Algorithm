package MemorySimulator;
import MemorySimulator.Memory.*;
import MemorySimulator.Tasks.Task;
public class Main {
    static Mem memoria;
    public static void main(String[] args) {
        memoria = new Mem();
        Task tar1 = new Task("tar1", (short) 250, 23);
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