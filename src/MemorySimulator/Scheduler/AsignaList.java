package MemorySimulator.Scheduler;
import MemorySimulator.Queue.ListaLigada;
import MemorySimulator.Queue.Nodo;
import MemorySimulator.Tasks.Task;
import MemorySimulator.Tasks.TaskGenerator;

public class AsignaList {
    private int aux1 =0, aux2 =0, aux3=0, aux4=0; 
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
                if(aux1<=aux2){
                    lista1(task);
                    lis.quitarNodInic();
                }
            }
            //lo que se podria hacer es modificar los menor que para que asi se ordene con cada if 
            //cada if deberia de tener un if en el que dependiendo de auxN se asigne o no 
            if( task.getTamano()<175 ){
                if(aux2<aux1 && task.getTamano()<121){
                    lista2(task);
                    lis.quitarNodInic();
                }
                if(aux2<=aux3 && task.getTamano()>120 ){
                    lista2(task);
                    lis.quitarNodInic();
                }
                
            }
            if(task.getTamano()>120 && task.getTamano()<231 ){
                if(aux3<aux2 && task.getTamano()<175){
                    lista3(task);
                    lis.quitarNodInic();
                }
                if(aux3<=aux4 && task.getTamano()>174){
                    lista3(task);
                    lis.quitarNodInic();
                }
                
            }
            if(task.getTamano()>174 && task.getTamano()<501 ){
                if(aux4<aux3 && task.getTamano()<231){
                    lista4(task);
                    lis.quitarNodInic();
                }
                if(task.getTamano()>230){
                    lista4(task);
                    lis.quitarNodInic();
                }
            }
            if(task.getTamano() > 500){
                System.out.println("Proceso: "+ task.getName()+ " eliminado");
                lis.quitarNodInic();
            }

            referencia = referencia.getSig();
        
        }
    }

    public int getAux1(){
        return aux1;
    }

    public int getAux2(){
        return aux2;
    }

    public int getAux3(){
        return aux3;
    }

    public int getAux4(){
        return aux4;
    }

    //Muy posible de BORRAR
    public void reasig(){
        if(getAux1()<getAux2()){
            lis1.agregarNodo(null);
        }
    }

    public ListaLigada lista1(Task proces ){
        lis1.agregarNodo(proces);
        aux1 = aux1+proces.getTiempo();
        return lis1;
    }

    

    public ListaLigada lista2(Task proces ){
        lis2.agregarNodo(proces);
        aux2 = aux2+proces.getTiempo();
        return lis2;
    }

    public ListaLigada lista3(Task proces ){
        lis3.agregarNodo(proces);
        aux3 = aux3+proces.getTiempo();
        return lis3;
    }

    public ListaLigada lista4(Task proces ){
        lis4.agregarNodo(proces);
        aux4 = aux4+proces.getTiempo();
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


    

