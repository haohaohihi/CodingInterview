package Chapter1;

import java.util.Stack;

/**
 * 实现一个能够获取最小值的栈结构，时间复杂度的O(1)
 * 两个栈，一个正常保存数据，另一个保存最小值。
 * 方法1:
 * push方法:dataStack正常入栈，若minStack为空，或数据 小于等于 minStack的栈顶数据，则minStack入栈
 * pop方法:dataStack正常出栈，若dataStack出栈的数据 等于 minStack的栈顶数据，则minStack出栈
 * getMin方法:返回minStack的栈顶数据
 * 注意:pop和getMin方法中，栈为空的异常
 */
public class StackWithMin_1 {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public StackWithMin_1(){
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(Integer data){
        dataStack.push(data);
        if(minStack.empty() || (data <= minStack.peek())){
            minStack.push(data);
        }
    }

    public Integer pop(){
        if(dataStack.empty() || minStack.empty()){
            throw new RuntimeException();
        }
        Integer value = dataStack.pop();
        if(value.equals(getMin())){
            minStack.pop();
        }
        return value;
    }

    public Integer getMin(){
        if(minStack.empty()){
            throw new RuntimeException();
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        StackWithMin_1 testStack = new StackWithMin_1();
        for(int i = 5; i < 10; i++){
            testStack.push(i);
        }
        for(int i=5; i >=0; i--){
            testStack.push(i);
        }

        System.out.println(testStack.getMin());
        for(int i = 0; i < 6; i++){
            System.out.println(testStack.pop());
            System.out.println("Current Min: " + testStack.getMin());
        }

    }
}
