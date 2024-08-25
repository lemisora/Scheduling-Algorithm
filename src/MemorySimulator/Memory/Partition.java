package MemorySimulator.Memory;

public class Partition {
    private final short memory_start;
    private final short memory_end;
    private final short memory_size;
    private boolean is_free = true;
    private String process_name;


    public Partition(short memory_start, short memory_size) {
        this.memory_start = memory_start;
        this.memory_size = memory_size;
        this.memory_end = (short) (memory_start + memory_size);
    }

    public boolean IsFree() {
        return is_free;
    }
    public boolean ItFits(short process_size) {
        return process_size <= memory_size;
    }

    public boolean AllocateProcess(String process_name, short process_size, Mem memory) {
        if (is_free && ItFits(process_size)) {
            this.process_name = process_name;
            is_free = false;
            for (short i = memory_start; i < memory_end; i++) {
                memory.available[i] = false;
            }
            return true;
        }
        return false;
    }

    public void DeallocateProcess(Mem memory) {
        is_free = true;
        process_name = null;
        for (short i = memory_start; i < memory_end; i++) {
            memory.available[i] = true;
        }
    }

    public boolean Overlaps(Partition partition) {
        return (this.memory_start >= partition.memory_start && this.memory_start <= partition.memory_end) ||
                (this.memory_end >= partition.memory_start && this.memory_end <= partition.memory_end);
    }
}