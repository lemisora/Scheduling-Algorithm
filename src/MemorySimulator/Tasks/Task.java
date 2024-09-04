package MemorySimulator.Tasks;
public class Task {
    private final String nombre;
    private final int tamano;
    private final int tiempo;

    public Task(String name, int tam, int time){
        this.nombre = name;
        this.tamano = tam;
        this.tiempo = time;
    }
    public String getName(){
        return this.nombre;
    }
    public int getTamano(){
        return this.tamano;
    }
    public int getTiempo(){
        return this.tiempo;
    }
}