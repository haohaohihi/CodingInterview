package Chapter1;

import java.util.Stack;

public class ReverseStack {
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int result = stack.pop();
        if(stack.isEmpty()){
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack){
        if(stack.empty()){
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(22);
        for(int i=0; i < 10; i++){
            stack.push(i);
        }
        System.out.println(getAndRemoveLastElement(stack));
        System.out.println(stack);
        reverse(stack);
        System.out.println(stack);
    }
}
