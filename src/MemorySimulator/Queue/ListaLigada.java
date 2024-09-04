package MemorySimulator.Queue;

import MemorySimulator.Tasks.Task;

public class ListaLigada {
    private Nodo head;

    public ListaLigada(){
        this.head=null;
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
    }

    public void quitarNodInic(){
        if(estaVacia()) {
			System.out.println("No se puede realizar debido a que la lista esta vacia \n ");
		}else {
			head = head.sig;
		}
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
}
