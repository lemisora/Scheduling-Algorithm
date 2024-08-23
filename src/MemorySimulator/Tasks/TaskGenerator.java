package MemorySimulator.Tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskGenerator {

    private static short MAX_BYTES = 1000;
    private static int MAX_TIME = 15; // segundos

    // imprime los datos de una tarea
    public static void printTask(Task task){
        System.out.println(task.getName() + "\t" + task.getTamano() + " bytes\t" + task.getTiempo() + " seconds");
    }

    // genera una tarea aleatoria
    public Task generateTask(){
        // se una semilla (obtenida del tiempo al parecer) para generar un número aleatorio
        Random random = new Random(System.currentTimeMillis());
        // se genera una tarea con un nombre default, un tamaño aleatorio entre 1 y 1000bytes y un tiempo aleatorio entre 1 y 15
        return new Task("New Task Generate", (short) (random.nextInt(MAX_BYTES)+1) , random.nextInt(MAX_TIME) +1);
    }

    public Task[] generateManyTasks(int numberTasks){
        // se una semilla (obtenida del tiempo al parecer) para generar un número aleatorio
        Random random = new Random(System.currentTimeMillis());

        // generamos el arreglo dinamico
        Task[] tasks = new Task[numberTasks];
        // generamos las tareas pedidas en los args
        for(int i = 0; i < numberTasks; i++){
            tasks[i] = new Task("Task "+(i+1) , (short) (random.nextInt(MAX_BYTES)+1) , random.nextInt(MAX_TIME) + 1);
        }
        // regresamos unicamente las tareas
        return tasks;
    }

    public static void exportTasksToFile(Task[] tasks){
        try {
            // se crea un archivo tasks.txt
            FileWriter writer = new FileWriter("tasks.txt");
            // se escribe en el archivo las tareas con las tareas dadas en args
            for(Task task : tasks){
                // ojo, no hay \n por lo que el archivo no contiene saltos de linea
                writer.write(task.getName() + "," + task.getTamano() + "," + task.getTiempo() + ",");
            }
            writer.close();
            System.out.println("Tasks exported to tasks.txt");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Something went wrong");
        }
    }

    public Task[] importTasksFromFile(String filename) {
        // se lee el archivo tasks.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            List<Task> tasks = new ArrayList<>();
            // el archivo a no contener saltos de linea, se puede leer todo el archivo de una vez
            line = reader.readLine();
            String[] parts = line.split(",");

            // se importan las tareas del archivo
            for (int i = 0; i < parts.length; i += 3) {
                tasks.add(new Task(parts[i], Short.parseShort(parts[i + 1]), Integer.parseInt(parts[i + 2])));
            }
            
            // se regresan las tareas
            return tasks.toArray(new Task[0]);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Something went wrong");
        }
        // si falla algo en el try, se regresa null
        return null;
    }

    /* public static void main(String[] args) {
        System.out.println("ONLY TEST PURPOSE\n");
        // se genera constructor
        TaskGenerator taskGenerator = new TaskGenerator(); 

        System.out.println("Generating a task");
        // Se genera una prueba unitaria
        TaskGenerator.printTask(taskGenerator.generateTask());

        System.out.println("\nGenerating many tasks");
        // Se generan varias pruebas unitarias
        Task[] tasks = taskGenerator.generateManyTasks(20);
        for(Task task : tasks){
            TaskGenerator.printTask(task);
        }

        System.out.println("\nExporting tasks to file");
        // Se exporta la prueba anterior a un archivo .txt
        TaskGenerator.exportTasksToFile(tasks);

        System.out.println("\nImporting tasks from file");
        // Se importa la prueba anterior desde un archivo .txt
        Task[] importedTasks = taskGenerator.importTasksFromFile("tasks.txt");
        for(Task task : importedTasks){
            TaskGenerator.printTask(task);
        }
    } */
}