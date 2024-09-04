package MemorySimulator.Memory;

public class Mem {
    private final int MEM_RANGE = 1024; // La memoria tiene un rango de 1024 bytes
    private final int MEM_PARTS = 4;   //Cantidad de particiones que tendrá la memoria
    public Partition [] partitions; //Con un arreglo del objeto partition se mandarán a llamar los métodos para manejar memoria

    //Tamanios de las particiones
    public final int prim_part = 230;
    public final int seg_part = 120;
    public final int ter_part = 500;
    public final int cuarta_part = 174;
    
    public Mem(){
        // Crear particiones
        partitions = new Partition[MEM_PARTS];
        //La primera particion comienza en 0 y termina en 230
        partitions[0] = new Partition(0, prim_part);
        //La segunda particion comienza en 230 y termina en 350
        partitions[1] = new Partition(partitions[0].getMemory_end(), seg_part);
        //La tercera particion comienza en 350 y termina en 850
        partitions[2] = new Partition(partitions[1].getMemory_end(), ter_part);
        //La cuarta particion comienza en 850 y termina en 1024
        partitions[3] = new Partition(partitions[2].getMemory_end(), cuarta_part);

        for(int i = 0; i < MEM_PARTS; i++){
            System.out.println("Tamaño de la partición "+(i+1)+": "+partitions[i].getMemory_size()+"\nInicio: "+partitions[i].getMemory_start()+"\tFin: "+partitions[i].getMemory_end());
        }

        if(prim_part+seg_part+ter_part+cuarta_part == MEM_RANGE){
            System.out.println("Se ha inicializado la memoria correctamente");
        } else {
            System.err.println("No se ha podido inicializar la memoria correctamente");
        }
    }

    public int MemoryParts(){
        return MEM_PARTS;
    }

    public boolean partition_allocate_process(int partition, String process_name,  int process_size){
        return partitions[partition].AllocateProcess(process_name, process_size, this);
    }

    public void partition_deallocate_process(int partition){
        partitions[partition].DeallocateProcess(this);
    }
}