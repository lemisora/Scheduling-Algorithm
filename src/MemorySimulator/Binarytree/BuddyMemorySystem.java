package MemorySimulator.Binarytree;

public class BuddyMemorySystem {
    private final int TOTAL_MEMORY = 2048; // Memoria total en KB (2 MB)
    private Nodo root; // Raíz del árbol que representa toda la memoria

    public BuddyMemorySystem() {
        // El árbol comienza con un solo bloque de 2048 KB
        root = new Nodo(new Block(TOTAL_MEMORY));
    }

    // Clase interna que representa un bloque de memoria
    private class Block {
        int size; // Tamaño del bloque en KB
        boolean isFree; // Si el bloque está disponible o no

        public Block(int size) {
            this.size = size;
            this.isFree = true;
        }
    }

    // Clase Nodo para el árbol binario
    private class Nodo {
        Block block; // El bloque de memoria que representa el nodo
        Nodo izquierdo, derecho; // Hijos izquierdo y derecho

        public Nodo(Block block) {
            this.block = block;
            this.izquierdo = null;
            this.derecho = null;
        }

        public boolean isLeaf() {
            return izquierdo == null && derecho == null;
        }
    }

    // Método para asignar memoria
    public boolean allocate(int size) {
        return allocateRecursive(root, size);
    }

    // Asignar memoria recursivamente
    private boolean allocateRecursive(Nodo nodo, int size) {
        if (nodo == null) return false;

        if (nodo.isLeaf()) {
            // Si el nodo es una hoja y es del tamaño adecuado, se asigna memoria
            if (nodo.block.isFree && nodo.block.size == size) {
                nodo.block.isFree = false;
                System.out.println("Memoria asignada: " + size + " KB");
                return true;
            }

            // Si el nodo es mayor, se divide en dos "buddies" y se asigna
            if (nodo.block.isFree && nodo.block.size > size) {
                divideNode(nodo);
                return allocateRecursive(nodo.izquierdo, size) || allocateRecursive(nodo.derecho, size);
            }
        }

        // Intentar asignar en los hijos izquierdo o derecho
        return allocateRecursive(nodo.izquierdo, size) || allocateRecursive(nodo.derecho, size);
    }

    // Método para dividir un nodo en dos bloques más pequeños
    private void divideNode(Nodo nodo) {
        if (nodo.block.size <= 1) return; // No se puede dividir más

        // Dividir el bloque en dos "buddies"
        int newSize = nodo.block.size / 2;
        nodo.izquierdo = new Nodo(new Block(newSize));
        nodo.derecho = new Nodo(new Block(newSize));
        nodo.block.isFree = false;
    }

    // Método para liberar memoria
    public boolean free(int size) {
        return freeRecursive(root, size);
    }

    // Liberar memoria recursivamente
    private boolean freeRecursive(Nodo nodo, int size) {
        if (nodo == null) return false;

        if (nodo.isLeaf()) {
            // Si es una hoja y coincide el tamaño, se libera la memoria
            if (!nodo.block.isFree && nodo.block.size == size) {
                nodo.block.isFree = true;
                System.out.println("Memoria liberada: " + size + " KB");
                return true;
            }
        }

        // Intentar liberar en los hijos izquierdo o derecho
        boolean leftFreed = freeRecursive(nodo.izquierdo, size);
        boolean rightFreed = freeRecursive(nodo.derecho, size);

        // Si ambos "buddies" son libres, se combinan
        if (leftFreed && rightFreed && nodo.izquierdo.block.isFree && nodo.derecho.block.isFree) {
            nodo.izquierdo = null;
            nodo.derecho = null;
            nodo.block.isFree = true;
            System.out.println("Buddies combinados: " + nodo.block.size + " KB");
        }

        return leftFreed || rightFreed;
    }

    // Método para mostrar el estado de la memoria
    public void printMemoryStatus() {
        printMemoryStatusRecursive(root, "");
    }

    // Mostrar el estado de la memoria de manera recursiva
    private void printMemoryStatusRecursive(Nodo nodo, String indent) {
        if (nodo == null) return;

        System.out.println(indent + "Bloque: " + nodo.block.size + " KB - " + (nodo.block.isFree ? "Libre" : "Ocupado"));
        printMemoryStatusRecursive(nodo.izquierdo, indent + "  ");
        printMemoryStatusRecursive(nodo.derecho, indent + "  ");
    }
}

