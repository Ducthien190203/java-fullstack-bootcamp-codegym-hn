class Node {
    int data;
    Node link;

    public Node(int data) {
        this.data = data;
        this.link = null;
    }
}

public class CircularQueue {
    Node front, rear;

    public CircularQueue() {
        front = rear = null;
    }

    public void enQueue(int value) {
        Node newNode = new Node(value);
        if (front == null) {
            front = rear = newNode;
            rear.link = front;
        } else {
            rear.link = newNode;
            rear = newNode;
            rear.link = front;
        }
    }

    public int deQueue() {
        if (front == null) {
            System.out.println("Hàng đợi rỗng!");
            return -1;
        }

        int value;
        if (front == rear) {
            value = front.data;
            front = rear = null;
        } else {
            value = front.data;
            front = front.link;
            rear.link = front;
        }
        return value;
    }

    public void displayQueue() {
        if (front == null) {
            System.out.println("Hàng đợi rỗng!");
            return;
        }

        Node temp = front;
        System.out.print("Queue: ");
        do {
            System.out.print(temp.data + " ");
            temp = temp.link;
        } while (temp != front);
        System.out.println();
    }
}
