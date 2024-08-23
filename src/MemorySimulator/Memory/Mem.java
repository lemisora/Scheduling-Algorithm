package MemorySimulator.Memory;
public class Mem {
    public final short MEM_RANGE 1024;
    public final short MEM_PARTS 4;
    public short[] memory;
    public short prim_part = 230;
    public short seg_part = 120;
    public short ter_part = 500;
    public short cuarta_part = 174;
    public boolean[MEM_PARTS] available;

    
    public Mem(){
        memory = new short[MEM_RANGE];
        available = new boolean[];
        for(int i = 0; i < MEM_PARTS; i++)
            available[i] = false;
        System.out.println("Se ha inicializado la memoria correctamente");
    }

    public int Allocator(int part, String id_Task){
        if(available[part] == false) {
            available[part] = true;
            return 0;
        }else {
            System.out.println("No se puede ejecutar el programa " + id_Task + " ya que la partición de memoria " + (part + 1) + " está ocupada");
            return -1;
        }
    }
}