package MemorySimulator.Memory;

public class Partition {
    private final int memory_start;
    private final int memory_end;
    private final int memory_size;
    private boolean is_free = true;
    private String process_name;


    public Partition(int memory_start, int memory_size) {
        this.memory_start = memory_start;
        this.memory_size = memory_size;
        this.memory_end = memory_start + memory_size;
    }

    public int getMemory_start() {
        return memory_start;
    }

    public int getMemory_end() {
        return memory_end;
    }

    public int getMemory_size() {
        return memory_size;
    }

    public boolean IsFree() {
        return is_free;
    }
    public boolean ItFits(int process_size) {
        return process_size <= memory_size;
    }

    public boolean AllocateProcess(String process_name, int process_size, Mem memory) {
        if (is_free && ItFits(process_size)) {
            this.process_name = process_name;
            is_free = false;
            return true;
        }
        return false;
    }

    public void DeallocateProcess(Mem memory) {
        is_free = true;
        process_name = null;
    }
}