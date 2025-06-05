public class MyLinkedList<E> {
    private Node head;
    private int numNodes = 0;

    private class Node {
        private E data;
        private Node next;

        public Node(E data) {
            this.data = data;
        }
    }

    // Constructor: khởi tạo danh sách với một phần tử
    public MyLinkedList(E element) {
        head = new Node(element);
        numNodes++;
    }

    // Thêm vào vị trí bất kỳ
    public void add(int index, E element) {
        if (index < 0 || index > numNodes) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == 0) {
            addFirst(element);
        } else {
            Node temp = head;
            for (int i = 0; i < index - 1 && temp != null; i++) {
                temp = temp.next;
            }

            Node holder = temp.next;
            temp.next = new Node(element);
            temp.next.next = holder;
            numNodes++;
        }
    }

    // Thêm vào đầu danh sách
    public void addFirst(E element) {
        Node temp = head;
        head = new Node(element);
        head.next = temp;
        numNodes++;
    }

    // Thêm vào cuối danh sách
    public void addLast(E element) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(element);
        numNodes++;
    }

    // Lấy phần tử tại vị trí index
    public E get(int index) {
        if (index < 0 || index >= numNodes) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    // Xóa phần tử tại vị trí index
    public E remove(int index) {
        if (index < 0 || index >= numNodes) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node temp = head;
        if (index == 0) {
            E removed = head.data;
            head = head.next;
            numNodes--;
            return removed;
        }

        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }

        E removed = temp.next.data;
        temp.next = temp.next.next;
        numNodes--;
        return removed;
    }

    // Xóa phần tử theo giá trị
    public boolean remove(Object o) {
        if (head == null) return false;

        if (head.data.equals(o)) {
            head = head.next;
            numNodes--;
            return true;
        }

        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data.equals(o)) {
                temp.next = temp.next.next;
                numNodes--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Trả về số phần tử
    public int size() {
        return numNodes;
    }

    // In danh sách
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Clone danh sách
    public MyLinkedList<E> clone() {
        if (numNodes == 0) return null;

        MyLinkedList<E> cloneList = new MyLinkedList<>(head.data);
        Node temp = head.next;

        while (temp != null) {
            cloneList.addLast(temp.data);
            temp = temp.next;
        }

        return cloneList;
    }

    // Kiểm tra phần tử có tồn tại
    public boolean contains(E o) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.equals(o)) return true;
            temp = temp.next;
        }
        return false;
    }

    // Trả về vị trí của phần tử
    public int indexOf(E o) {
        Node temp = head;
        int index = 0;
        while (temp != null) {
            if (temp.data.equals(o)) return index;
            temp = temp.next;
            index++;
        }
        return -1;
    }
}
