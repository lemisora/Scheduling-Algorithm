package MemorySimulator.Scheduler;
//import MemorySimulator.Tasks.TaskGenerator;
import MemorySimulator.Queue.ListaLigada;
import MemorySimulator.Tasks.TaskGenerator;
public class Pruebas {
    
    public static void main(String[] args) {
        TaskGenerator clastask =new TaskGenerator();
        //clastask.generateManyTasks(20);
        clastask.exportTasksToFile( clastask.generateManyTasks(5));
        //clastask.importTasksFromFile("tasks.txt");
        //TaskGenerator clastask =new TaskGenerator();
        AsignaList asigna =new AsignaList(clastask.importTasksFromFile("tasks.txt"));

        //clastask.generateManyTasks(20);
        //clastask.exportTasksToFile( clastask.generateManyTasks(20));
        //clastask.importTasksFromFile("tasks.txt");
        asigna.despacho();
        asigna.getLista1();
        asigna.getLista2();
        asigna.getLista3();
        asigna.getLista4();
        Sched2 imprimeLista1 = new Sched2(asigna.getLista1(),"Lista1");
        Sched2 imprimeLista2 = new Sched2(asigna.getLista2(),"Lista2");
        Sched2 imprimeLista3 = new Sched2(asigna.getLista3(),"Lista3");
        Sched2 imprimeLista4 = new Sched2(asigna.getLista4(),"Lista4");



        /*si se creara otra clase en la que se mandan a llamar los metodos de 
        clastask.generateManyTasks(20);
        clastask.exportTasksToFile( clastask.generateManyTasks(20));
        clastask.importTasksFromFile("tasks.txt");
        podriamos crear un metodo que mande a llamar la clase mem con sus metodos para ir filtrando y clasificando 
        los task de acuerdo al tamaño de las particiones, y se almacenarian en cuatro listas respectivamente
        desde cuatro metodos 



        ----se crean cuatro metodos cada uno recibe un nodo como parametro, esos metodos enlistan el nodo en una 
        ----linkedlist y despues retornan esa linked list. 

        se crea un metodo que recorre una linked list y va filtrando los task por tamaño, dependiendo del tamaño
        manda a traer el metodo (de los mencionados arriba) y manda ese nodo a la respectiva lista.

        Por lo tanto de esa clase obtendriamos cuatro listas ligadas de cuatro metodos diferentes, por lo que 
        se crearian cuatro objetos Sched2 y se mandan a llamar respectivamente esos metodos(que retornan la linkedlist)
        como parametros de Sched2, despues se pasa cada objeto Sched2 como parametro a cada hilo respectivamente


        */
        


        Thread hilo1 =new Thread(imprimeLista1);
        Thread hilo2 =new Thread(imprimeLista2);
        Thread hilo3 =new Thread(imprimeLista3);
        Thread hilo4 =new Thread(imprimeLista4);

        long starTime =  System.currentTimeMillis();

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();


        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            long endtime = System.currentTimeMillis();
            long excutionTime = endtime-starTime;
            System.out.println("tiempo de ejecucuion: "+ (excutionTime/1000)+ "segundos ");
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido.");
        }


    }
}
