package MemorySimulator.Tasks;
import MemorySimulator.Queue.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskGenerator {
    //instancia de la lista ligada para que podamos utilizar el metodo de "encolar" 
    private ListaLigada LiLa =new ListaLigada();
    
    private final short MAX_BYTES = 1000;
    private final int MAX_TIME = 50; // segundos

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
    public Task generateTask(String name){
        // se una semilla (obtenida del tiempo al parecer) para generar un número aleatorio
        Random random = new Random(System.currentTimeMillis());
        // se genera una tarea con un nombre default, un tamaño aleatorio entre 1 y 1000bytes y un tiempo aleatorio entre 1 y 15
        return new Task(name, (short) (random.nextInt(MAX_BYTES)+1) , random.nextInt(MAX_TIME) +1);
    }
    public Task generateTask(String name, short size, short seconds_time){
        // Genera un task con los valores enviados
        return new Task(name, size, seconds_time);
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

    public ListaLigada importTasksFromFile(String filename) {
        // se lee el archivo tasks.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            List<Task> tasks = new ArrayList<>();
            // el archivo a no contener saltos de linea, se puede leer todo el archivo de una vez
            line = reader.readLine();
            String[] parts = line.split(",");

            System.out.println("IMPORTANDO TASKS DEL ARCHIVO TXT \n");
            // se importan las tareas del archivo
            for (int i = 0; i < parts.length; i += 3) {
                //se crea una instancia nueva con los atributos de cada iteracion
                Task proces = new Task(parts[i], Short.parseShort(parts[i + 1]), Integer.parseInt(parts[i + 2]));
                //se agrega esa instancia a la Linkedlist
                LiLa.agregarNodo(proces);
                System.out.println("\n Nombre: "+proces.getName()+", Tamanio: "+proces.getTamano()+", Tiempo: "+proces.getTiempo());
                //tasks.add(new Task(parts[i], Short.parseShort(parts[i + 1]), Integer.parseInt(parts[i + 2])));
            }
            System.out.println("TASK CARGADOS EN LINKEDLIST INICIAL \n");
            // se regresan las tareas
            return LiLa;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Something went wrong");
        }
        // si falla algo en el try, se regresa null
        return null;
    }

//    public void task_generator_tester_app(){
//        System.out.println("ONLY TEST PURPOSE\n");
//        // se genera constructor
//        TaskGenerator taskGenerator = new TaskGenerator();

//        System.out.println("Generating a task");
//        // Se genera una prueba unitaria
//        TaskGenerator.printTask(taskGenerator.generateTask());

//        System.out.println("\nGenerating many tasks");
//        // Se generan varias pruebas unitarias
//        Task[] tasks = taskGenerator.generateManyTasks(20);
//        for(Task task : tasks){
//            TaskGenerator.printTask(task);
//        }

//        System.out.println("\nExporting tasks to file");
//        // Se exporta la prueba anterior a un archivo .txt
//        TaskGenerator.exportTasksToFile(tasks);

//        System.out.println("\nImporting tasks from file");
//        // Se importa la prueba anterior desde un archivo .txt
//        ListaLigada importedTasks = taskGenerator.importTasksFromFile("tasks.txt");
       
//        if(importedTasks.estaVacia()) {
//         System.out.println("No se puede realizar debido a que la lista esta vacia \n ");
//         }else {
//         Nodo nodoactual = importedTasks.head;
//         while(nodoactual.getSig() != null) {
//             printTask(nodoactual.getTask());
//             nodoactual = nodoactual.sig;
//         }
//     }
//    }
}