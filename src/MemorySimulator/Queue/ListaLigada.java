package MemorySimulator.Queue;

import MemorySimulator.Tasks.Task;

public class ListaLigada {
    private Nodo head;
    private int size=0;

    public ListaLigada(){
        this.head=null;
    }

    public int getSize(){
        return size;
    }

    public boolean estaVacia(){
        return head == null;                
    }

    public Nodo getHead(){
        return head;
    }
    public void agregarNodo(Task proceso){
        if(estaVacia()){
            Nodo nuevoNodo = new Nodo(proceso);
            head = nuevoNodo;
        }else{
            Nodo nuevoNodo = new Nodo(proceso);
            Nodo referencia = head;
            while (referencia.sig != null) {
                referencia = referencia.sig;
            }
            referencia.sig = nuevoNodo;
        }
        size++;
    }

    public void recorrido() {
		if(estaVacia()) {
			System.out.println("No se puede realizar debido a que la lista esta vacia \n ");
		}else {
			Nodo nodoactual = head;
			while(nodoactual.getSig() != null) {
				nodoactual = nodoactual.sig;
			}
		}
	}

    public Nodo pull_node() {
        if(estaVacia()) {
            // System.err.println("No se puede realizar debido a que la lista esta vacia \n ");
            return null;
        }else {
            Nodo nodoactual = head;
            this.head = nodoactual.getSig();
            size--;
            return nodoactual;
        }
    }
    public void imprimirContenido() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
        } else {
            Nodo nodoActual = head;
            while (nodoActual != null) {
                Task tarea = nodoActual.getTask();
                System.out.println(tarea.getName() + "\t" + tarea.getTamano() + " bytes\t" + tarea.getTiempo() + " seconds");
                nodoActual = nodoActual.getSig();
            }
        }
    }

}
