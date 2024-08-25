package MemorySimulator.Scheduler;
import MemorySimulator.Memory.Mem;
import MemorySimulator.Memory.Partition;
import MemorySimulator.Tasks.Task;
import java.util.Arrays;


public class sched {
    /*
    * Para el algoritmo de despacho se usará un modelo FIFO (el primero que entra será el primero en salir)
    *
    * */
    public sched(Mem memory, Task[] task){
        System.out.println("Iniciando Algoritmo");

        short partition_to_use = 0;
        Task current_task;
        int index_task = -1;
        int discarded_tasks = 0;
        int completed_tasks = 0;

        while (task.length > index_task){
            current_task = task[index_task];
            partition_to_use = -1;

            System.out.println("Procesando tarea: " + current_task.getName());
            
            // se busca una particion libre
            for (short i = 0; i < memory.partitions.length; i++) {
                if (memory.partitions[i].IsFree() && memory.partitions[i].ItFits(current_task.getTamano())) {
                    partition_to_use = i;
                    break;
                }
                
            }
            if (partition_to_use == -1) {
                System.err.println("No hay particiones disponibles para la tarea: " + current_task.getName() + " se ha descartado la tarea");
                discarded_tasks++;
                break;
            }

            // se asigna la tarea a la particion y poner algo de tiempo o no se
            if (memory.partition_allocate_process(partition_to_use, current_task.getName(), current_task.getTamano())) {
                System.out.println("Tarea asignada a la particion: " + partition_to_use);
                completed_tasks++;
            } else {
                System.err.println("No se pudo asignar la tarea: " + current_task.getName());
                discarded_tasks++;
                break;
            }
            
        }

    }

}