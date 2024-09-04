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
    
    public AsignaList(ListaLigada lis){
        this.lis = lis;
        
    }

    public void despacho(){
        
        Nodo referencia = lis.getHead();
        while(referencia != null){
            Task task =referencia.getTask();
            //230, 120, 500, 174
            if(task.getTamano()<121){
                lista1(task);
                lis.quitarNodInic();
            }
            if(task.getTamano()>120 && task.getTamano()<175 ){
                lista2(task);
                lis.quitarNodInic();
            }
            if(task.getTamano()>174 && task.getTamano()<231 ){
                lista3(task);
                lis.quitarNodInic();
            }
            if(task.getTamano()>230 && task.getTamano()<501 ){
                lista4(task);
                lis.quitarNodInic();
            }
            if(task.getTamano() > 501){
                System.out.println("Proceso: "+ task.getName()+ " eliminado");
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

    public ListaLigada getLista1(){
        return lis1;
    }

    public ListaLigada getLista2(){
        return lis2;
    }

    public ListaLigada getLista3(){
        return lis3;
    }

    public ListaLigada getLista4(){
        return lis4;
    }

        
    }


    

