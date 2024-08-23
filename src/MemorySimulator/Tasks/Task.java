package MemorySimulator.Tasks;
public class Task {
    private String nombre;
    private short tamaño;
    private int tiempo;

    public Task(String name, short tam, int time){
        this.nombre = name;
        this.tamaño = tam;
        this.tiempo = time;
    }
}