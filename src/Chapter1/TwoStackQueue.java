package Chapter1;

import java.util.Stack;

public class TwoStackQueue {
    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    public TwoStackQueue() {
        this.pushStack = new Stack<>();
        this.popStack = new Stack<>();
    }

    public void add(Integer data) {
        this.pushStack.add(data);
    }

    public Integer remove() {
        transfer();
        return popStack.pop();
    }

    public Integer element() {
        transfer();
        return popStack.peek();
    }

    private void transfer() {
        if (pushStack.empty() && popStack.empty()) {
            throw new RuntimeException();
        } else if (popStack.empty()) {
            while (!pushStack.empty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        TwoStackQueue queue = new TwoStackQueue();
        for(int i=0; i < 10; i++){
            queue.add(i * i);
        }
        for(int i=0; i < 10; i++){
            System.out.print(queue.remove() + ", ");
        }
    }

}
