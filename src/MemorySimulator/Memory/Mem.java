package MemorySimulator.Memory;

public class Mem {
    public final short MEM_RANGE = 1024; // La memoria tiene un rango de 1024 bytes
    public final short MEM_PARTS = 4;   //Cantidad de particiones que tendrá la memoria

    public char[] memory; // tiene la informacion de la memoria
    //public boolean[] available; // Con un arreglo de booleanos se determinará que partición se encuentra disponible
    public Partition [] partitions; //Con un arreglo del objeto partition se mandarán a llamar los métodos para manejar memoria

    //Tamanios de las particiones
    public short prim_part = 230;
    public short seg_part = 120;
    public short ter_part = 500;
    public short cuarta_part = 174;
    
    public Mem(){
        memory = new char[MEM_RANGE];
        //available = new boolean[MEM_PARTS];
//        for(int i = 0; i < MEM_PARTS; i++){
//            //available[i] = true;
//        }

        // Crear particiones
        partitions = new Partition[MEM_PARTS];
        //La primera particion comienza en 0 y termina en 230
        partitions[0] = new Partition((short) 0, prim_part);
        //La segunda particion comienza en 230 y termina en 350
        partitions[1] = new Partition(partitions[0].getMemory_end(), seg_part);
        //La tercera particion comienza en 350 y termina en 850
        partitions[2] = new Partition(partitions[1].getMemory_end(), ter_part);
        //La cuarta particion comienza en 850 y termina en 1024
        partitions[3] = new Partition(partitions[2].getMemory_end(), cuarta_part);

        for(int i = 0; i < MEM_PARTS; i++){
            System.out.println("Tamaño de la partición "+(i+1)+": "+partitions[i].getMemory_size()+"\nInicio: "+partitions[i].getMemory_start()+"\tFin: "+partitions[i].getMemory_end());
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

    public boolean fits_in_partition(short partition, short process_size){
        return partitions[partition].ItFits(process_size);
    }
   

//    public static void main(String[] args) {
//        Mem mem = new Mem();
//        // check if partitions are ok
//        if (mem.prim_part + mem.seg_part + mem.ter_part + mem.cuarta_part != 1024){
//            System.out.println("Error en la asignación de particiones de memoria");
//            System.out.println("La suma de las particiones es: " + (mem.prim_part + mem.seg_part + mem.ter_part + mem.cuarta_part));
//        } else {
//            System.out.println("Particiones de memoria asignadas correctamente");
//        }
//    }
}