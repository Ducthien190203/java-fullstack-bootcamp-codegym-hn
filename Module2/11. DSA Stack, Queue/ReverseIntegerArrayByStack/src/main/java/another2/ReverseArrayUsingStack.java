package another2;

import java.util.Stack;

public class ReverseArrayUsingStack {
    public static void reverseArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        for (int num : arr) {
            stack.push(num);
        }
        // Pop elements from the stack and fill the array in reverse order
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.pop();
        }
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
