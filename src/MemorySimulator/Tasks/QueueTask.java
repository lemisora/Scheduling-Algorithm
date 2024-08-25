package MemorySimulator.Tasks;

public class QueueTask {
    private Task[] tasks;
    private int size;
    private int front;
    private int rear;

    public QueueTask(int size) {
        this.size = size;
        this.tasks = new Task[size];
        this.front = 0;
        this.rear = 0;
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public boolean isFull() {
        return (this.rear + 1) % this.size == this.front;
    }

    public void enqueue(Task task) {
        if (!isFull()) {
            this.tasks[this.rear] = task;
            this.rear = (this.rear + 1) % this.size;
        } else {
            System.out.println("Queue is full");
        }
    }

    public Task dequeue() {
        if (!isEmpty()) {
            Task task = this.tasks[this.front];
            this.front = (this.front + 1) % this.size;
            return task;
        } else {
            System.out.println("Queue is empty");
            return null;
        }
    }

    public Task peek() {
        if (!isEmpty()) {
            return this.tasks[this.front];
        } else {
            System.out.println("Queue is empty");
            return null;
        }
    }

    public void printQueue() {
        if (!isEmpty()) {
            int i = this.front;
            while (i != this.rear) {
                Task task = this.tasks[i];
                System.out.println(task.getName() + "\t" + task.getTamano() + " bytes\t" + task.getTiempo() + " seconds");
                i = (i + 1) % this.size;
            }
        } else {
            System.out.println("Queue is empty");
        }
    }
}