public class InsertionSort {
    public static void insertionSort(int[] array){
        for(int i=0;i<array.length;i++){
            int currentElement = array[i];
            int j = i - 1;
            while(j>=0&&currentElement<array[j]){
                array[j+1] = array[j];
                j--;
            }
            array[j+1]=currentElement;
        }
    }

    public static void main(String[] args) {
        int[] list={2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        insertionSort(list);
        System.out.println("Sorted array:");
        for(int value:list){
            System.out.print(value + " ");
        }
    }
}
