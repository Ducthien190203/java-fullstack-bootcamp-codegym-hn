public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>("Java");

        list.addLast("Python");
        list.addLast("C++");
        list.addFirst("HTML");

        list.printList(); // HTML -> Java -> Python -> C++ -> null

        System.out.println("Size: " + list.size()); // 4

        System.out.println("Element at index 2: " + list.get(2)); // Python

        list.add(2, "JavaScript");
        list.printList(); // HTML -> Java -> JavaScript -> Python -> C++ -> null

        list.remove(1); // remove Java
        list.printList(); // HTML -> JavaScript -> Python -> C++ -> null

        list.remove("Python");
        list.printList(); // HTML -> JavaScript -> C++ -> null

        System.out.println("Contains C++: " + list.contains("C++")); // true
        System.out.println("Index of C++: " + list.indexOf("C++")); // 2

        MyLinkedList<String> cloneList = list.clone();
        System.out.print("Cloned List: ");
        cloneList.printList(); // HTML -> JavaScript -> C++ -> null
    }
}

