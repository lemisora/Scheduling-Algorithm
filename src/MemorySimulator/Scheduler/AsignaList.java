package MemorySimulator.Scheduler;
import MemorySimulator.Queue.ListaLigada;
import MemorySimulator.Queue.Nodo;
import MemorySimulator.Tasks.Task;
import MemorySimulator.Tasks.TaskGenerator;

public class AsignaList {
    private ListaLigada lis;
    ListaLigada lis1=new  ListaLigada();
    ListaLigada lis2=new  ListaLigada();
    ListaLigada lis3=new  ListaLigada();
    ListaLigada lis4=new  ListaLigada();
    //private Nodo nod;
    
    public AsignaList(){
        
    }

    public void despacho(){
        TaskGenerator clastask =new TaskGenerator();
        clastask.generateManyTasks(20);
        clastask.exportTasksToFile( clastask.generateManyTasks(20));
        clastask.importTasksFromFile("tasks.txt");
        Nodo referencia = lis.getHead();
        while(referencia != null){
            Task task =referencia.getTask();
            //230, 120, 500, 174
            if(task.getTamano() <= 120 ){
                lista1(task);
                lis.quitarNodInic();
            }
            if(task.getTamano() <= 174 ){
                lista2(task);
                lis.quitarNodInic();
            }
            if(task.getTamano() <= 230 ){
                lista3(task);
                lis.quitarNodInic();
            }
            if(task.getTamano() <= 500 ){
                lista4(task);
                lis.quitarNodInic();
            }
            if(task.getTamano() >= 500){
                System.out.println("Proceso eliminado");
                lis.quitarNodInic();
            }
            referencia = referencia.getSig();
        
        }
    }

    public ListaLigada lista1(Task proces ){
        lis1.agregarNodo(proces);
        return lis1;
    }

    public ListaLigada lista2(Task proces ){
        lis2.agregarNodo(proces);
        return lis2;
    }

    public ListaLigada lista3(Task proces ){
        lis3.agregarNodo(proces);
        return lis3;
    }

    public ListaLigada lista4(Task proces ){
        lis4.agregarNodo(proces);
        return lis4;
    }


        
    }


    

