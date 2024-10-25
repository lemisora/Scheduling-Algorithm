package MemorySimulator.Binarytree;

import MemorySimulator.Tasks.Task;
import MemorySimulator.Queue.Nodo;
import MemorySimulator.Queue.ListaLigada;
//import MemorySimulator.Binarytree.BuddyMemorySystem;

public class MemoryAllocator {
    private ListaLigada taskList;
    private BuddyMemorySystem buddyMemory;

    public MemoryAllocator(ListaLigada taskList) {
        this.taskList = taskList;
        this.buddyMemory = new BuddyMemorySystem();  // Instancia del sistema Buddy
    }

    // Método para asignar memoria a las tareas de la lista ligada
    public void allocateMemoryForTasks() {
        if (taskList.estaVacia()) {
            System.out.println("La lista de tareas está vacía, no se puede asignar memoria.");
            return;
        }

        // Recorremos la lista ligada y asignamos memoria a cada tarea
        Nodo current = taskList.getHead(); // Iniciamos desde la cabeza de la lista
        while (current != null) {
            Task task = current.getTask();
            int taskSize = task.getTamano();  // Tamaño de la tarea
            System.out.println("Asignando memoria para la tarea: " + task.getName() + " - Tamaño: " + taskSize + " KB");

            boolean success = buddyMemory.allocate(taskSize);  // Intentamos asignar memoria
            if (!success) {
                System.out.println("No se pudo asignar memoria para la tarea: " + task.getName() + " (Tamaño: " + taskSize + " KB)");
            }

            current = current.getSig();  // Pasamos al siguiente nodo
        }

        // Mostramos el estado de la memoria tras las asignaciones
        buddyMemory.printMemoryStatus();
    }
}
