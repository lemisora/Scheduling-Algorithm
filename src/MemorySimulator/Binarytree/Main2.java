package MemorySimulator.Binarytree;
import MemorySimulator.Queue.ListaLigada;
import MemorySimulator.Tasks.TaskGenerator;

public class Main2 {

    public static void main(String[] args) {
        TaskGenerator clastask =new TaskGenerator();
        clastask.exportTasksToFile( clastask.generateManyTasks(20));
        MemoryAllocator allocator = new MemoryAllocator(clastask.importTasksFromFile("tasks.txt"));
        allocator.allocateMemoryForTasks();
        
    }
    
}
