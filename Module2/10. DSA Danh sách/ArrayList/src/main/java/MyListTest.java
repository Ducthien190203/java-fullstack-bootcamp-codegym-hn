public class MyListTest {
    public static void main(String[] args) {
        MyList<String> list = new MyList<>();

        list.add("Java");
        list.add("Python");
        list.add("C++");

        System.out.println("List size: " + list.size());  // 3

        list.add(1, "JavaScript");
        System.out.println("Element at index 1: " + list.get(1)); // JavaScript

        list.set(2, "C#");
        System.out.println("Element at index 2 after set: " + list.get(2)); // C#

        list.remove(0);
        System.out.println("Element at index 0 after remove: " + list.get(0)); // JavaScript

        System.out.println("Final list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        list.clear();
        System.out.println("Size after clear: " + list.size()); // 0
    }
}
