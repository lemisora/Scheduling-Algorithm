package MemorySimulator.Queue;
import MemorySimulator.Tasks.Task;

public class Nodo {
    public Task proceso; 
	public Nodo sig;
	
	public Nodo(Task proces) {
		this.proceso=proces;
		this.sig=null;
	}

	public Task getTask() {
		return proceso;
	}

	public void setTask(Task proceso) {
		this.proceso = proceso;
	}

	public Nodo getSig() {
		return sig;
	}

	public void setSig(Nodo sig) {
		this.sig = sig;
	}
}
