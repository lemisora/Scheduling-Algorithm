package MemorySimulator.Tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskGenerator {
    public static void printTask(Task task){
        System.out.println(task.getName() + "\t" + task.getTamano() + " bytes\t" + task.getTiempo() + " seconds");
    }

    public Task generateTask(){
        Random random = new Random(System.currentTimeMillis());

        return new Task("New Task Generate", (short) (random.nextInt(100)+1) , random.nextInt(15));
    }

    public Task[] generateManyTasks(int numberTasks){
        Random random = new Random(System.currentTimeMillis());

        Task[] tasks = new Task[numberTasks];
        for(int i = 0; i < numberTasks; i++){
            tasks[i] = new Task("Task "+(i+1) , (short) (random.nextInt(100)+1) , random.nextInt(15) + 1);
        }
        return tasks;
    }

    public static void exportTasksToFile(Task[] tasks){
        try {
            FileWriter writer = new FileWriter("tasks.txt");
            for(Task task : tasks){
                writer.write(task.getName() + "," + task.getTamano() + "," + task.getTiempo() + ",");
            }
            writer.close();
            System.out.println("Tasks exported to tasks.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while exporting tasks to a file: " + e.getMessage());
        }
    }

    public Task[] importTasksFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            List<Task> tasks = new ArrayList<>();
            line = reader.readLine();
            String[] parts = line.split(",");

            for (int i = 0; i < parts.length; i += 3) {
                tasks.add(new Task(parts[i], Short.parseShort(parts[i + 1]), Integer.parseInt(parts[i + 2])));
            }
            
            
            return tasks.toArray(new Task[0]);
        } catch (IOException e) {
            System.out.println("An error occurred while importing tasks from a file: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
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
    }
}