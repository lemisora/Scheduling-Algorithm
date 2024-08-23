package MemorySimulator.Tasks;
public class Task {
    private String nombre;
    private short tamano;
    private int tiempo;

    public Task(String name, short tam, int time){
        this.nombre = name;
        this.tamano = tam;
        this.tiempo = time;
    }
    public String getName(){
        return this.nombre;
    }
    public short getTamano(){
        return this.tamano;
    }
    public int getTiempo(){
        return this.tiempo;
    }
}