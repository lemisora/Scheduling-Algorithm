package MemorySimulator.Memory;

// Simulamos la memoria de la computadora
public class Mem {
    // La memoria tiene un rango de 1024 bytes
    public final short MEM_RANGE = 1024;
    public final short MEM_PARTS = 4;
    public short[] memory;
    public String[] memory_task_names;
    public short prim_part = 230;
    public short seg_part = 120;
    public short ter_part = 500;
    public short cuarta_part = 174;
    public boolean[] available;

    
    public Mem(){
        memory = new short[MEM_RANGE];
        available = new boolean[MEM_PARTS];
        memory_task_names = new String[MEM_PARTS];
        for(int i = 0; i < MEM_PARTS; i++){
            available[i] = false;
            memory_task_names[i] = "";
        }
        System.out.println("Se ha inicializado la memoria correctamente");
    }

    public boolean Allocator(int part, String id_Task){
        if(available[part] == false) {
            available[part] = true;
            memory_task_names[part] = id_Task;
            // se regresa que si se uso la memoria
            return true;
        }else {
            System.err.println("No se puede ejecutar el programa " + id_Task + " ya que la partición de memoria " + (part + 1) + " está ocupada");
            System.err.println("El programa " + memory_task_names[part] + " está ocupando la partición de memoria " + (part + 1));
            return false;
        }
    }

    // se pondra a checar si la parte de la memoria pertenece a cierta tarea?
    public boolean Deallocator(int part, String id_Task){
        if(available[part] == true) {
            available[part] = false;
            // se regresa que la memoria esta libre
            return true;
        }else {
            System.err.println("No se puede liberar la memoria de la partición " + (part + 1) + " ya que no está ocupada");
            return false;
        }
    }

    // no creo que se ocupe, pero es un deslocalizador que checa que la tarea a borrar sea una dada
    public boolean DeallocatorSecure(int part, String id_Task){
        if(available[part] == true && memory_task_names[part].equals(id_Task)) {
            available[part] = false;
            // se regresa que la memoria esta libre
            return true;
        }else {
            if (available[part] == false){
                System.err.println("No se puede liberar la memoria de la partición " + (part + 1) + " ya que no está ocupada");
            } else {
                System.err.println("No se puede liberar la memoria de la partición " + (part + 1) + " ya que no pertenece a la tarea " + id_Task);
                System.err.println("La partición de memoria " + (part + 1) + " pertenece a la tarea " + memory_task_names[part]);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Mem mem = new Mem();
        // check if partitions are ok
        if (mem.prim_part + mem.seg_part + mem.ter_part + mem.cuarta_part != 1024){
            System.out.println("Error en la asignación de particiones de memoria");
            System.out.println("La suma de las particiones es: " + (mem.prim_part + mem.seg_part + mem.ter_part + mem.cuarta_part));
        } else {
            System.out.println("Particiones de memoria asignadas correctamente");
        }
    }
}