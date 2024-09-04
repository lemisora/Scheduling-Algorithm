package MemorySimulator.Tasks;

import MemorySimulator.Queue.*;
import MemorySimulator.Memory.Mem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TaskGenerator {
    //instancia de la lista ligada para que podamos utilizar el metodo de "encolar" 
    private ListaLigada LiLa = new ListaLigada();
    private static Mem mem_sample = new Mem();
    private static final int MAX_BYTES = 1000;
    private static final int MAX_TIME = 50; // segundos

    // imprime los datos de una tarea
    public static void printTask(Task task){
        System.out.println(task.getName() + "\t" + task.getTamano() + " bytes\t" + task.getTiempo() + " seconds");
    }

    public static Task[] generateManyTasks(int numberTasks){
        // se una semilla (obtenida del tiempo al parecer) para generar un número aleatorio
        Random random = new Random(System.currentTimeMillis());

        // generamos el arreglo dinamico
        Task[] tasks = new Task[numberTasks];
        // generamos las tareas pedidas en los args
        for(int i = 0; i < numberTasks; i++){
            tasks[i] = new Task("Task "+(i+1) ,   (random.nextInt(MAX_BYTES)+1) , random.nextInt(MAX_TIME) + 1);
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
            System.out.println("Tareas exportadas a tasks.txt");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("No se pudo escribir el archivo");
        }
    }

    public static ListaLigada importTasksFromFile(String filename) {
        // se lee el archivo tasks.txt
        ListaLigada LiLa = new ListaLigada();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // el archivo a no contener saltos de linea, se puede leer todo el archivo de una vez
            line = reader.readLine();
            String[] parts = line.split(",");

            // se importan las tareas del archivo
            for (int i = 0; i < parts.length; i += 3) {
                //se crea una instancia nueva con los atributos de cada iteracion
                Task proces = new Task(parts[i],  Integer.parseInt(parts[i + 1]), Integer.parseInt(parts[i + 2]));
                //se agrega esa instancia a la Linkedlist
                LiLa.agregarNodo(proces);
                //tasks.add(new Task(parts[i],  int.parse int(parts[i + 1]), Integer.parseInt(parts[i + 2])));
            }
            
            // se regresan las tareas
            return LiLa;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Something went wrong");
        }
        // si falla algo en el try, se regresa null
        return null;
    }

    public static ListaLigada[] importTasksFromFileToArray(String filename) {
        // se lee el archivo tasks.txt
        ListaLigada[] LiLa = new ListaLigada[mem_sample.MemoryParts()];
        for (int i = 0; i < mem_sample.MemoryParts(); i++) {
            LiLa[i] = new ListaLigada();
        }
        int part = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // el archivo a no contener saltos de linea, se puede leer todo el archivo de una vez
            line = reader.readLine();
            String[] parts = line.split(",");

            // se importan las tareas del archivo
            for (int i = 0; i < parts.length; i += 3) {
                //se crea una instancia nueva con los atributos de cada iteracion
                Task proces = new Task(parts[i],  Integer.parseInt(parts[i + 1]), Integer.parseInt(parts[i + 2]));
                //se agrega esa instancia a la Linkedlist correspondiente
                for(int j = 0; j < mem_sample.MemoryParts(); j++){
                    /* Mejoras a futuro
                    if(mem_sample.fits_in_partition(j, proces.getTamano() ) ){
                        LiLa[j].agregarNodo(proces);
                    }
                     */
                    LiLa[j].agregarNodo(proces);
                }
            }

            // se regresan las tareas
            return LiLa;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("No se han podido añadir las tareas");
        }
        // si falla algo en el try, se regresa null
        return null;
    }

}