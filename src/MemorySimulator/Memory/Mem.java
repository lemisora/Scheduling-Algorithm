package MemorySimulator.Memory;

public class Mem {
    // La memoria tiene un rango de 1024 bytes
    public final short MEM_RANGE = 1024;
    public char[] memory; // tiene la informacion de la memoria
    public boolean[] available; // esa parte de la memoria esta disponible
    Partition [] partitions;

    public final short MEM_PARTS = 4;
    public short prim_part = 230;
    public short seg_part = 120;
    public short ter_part = 500;
    public short cuarta_part = 174;



    
    public Mem(){
        memory = new char[MEM_RANGE];
        available = new boolean[MEM_PARTS];
        for(int i = 0; i < MEM_PARTS; i++){
            available[i] = false;
        }

        // crear particiones
        // no se como automatizar esto con lo que tenemos y necesitamos entregar
        partitions = new Partition[MEM_PARTS];
        
        partitions[0] = new Partition((short) 0, prim_part);

        partitions[1] = new Partition((short) prim_part, seg_part);

        partitions[2] = new Partition((short) seg_part, ter_part);

        partitions[3] = new Partition((short) ter_part, cuarta_part);
      
        // checar si no se solapan las particiones
        // capaz borramos esto
        for(int i = 0; i < MEM_PARTS; i++){
            for(int j = 0; j < MEM_PARTS; j++){
                if(i != j){
                    if(partitions[i].Overlaps(partitions[j])){
                        System.out.println("Error: Las particiones se solapan");
                    }
                }
            }
        }

        System.out.println("Se ha inicializado la memoria correctamente");
    }

    public boolean partition_is_free(short partition){
        return partitions[partition].IsFree();
    }

    public boolean partition_allocate_process(short partition, String process_name, short process_size){
        return partitions[partition].AllocateProcess(process_name, process_size, this);
    }

    public void partition_deallocate_process(short partition){
        partitions[partition].DeallocateProcess(this);
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