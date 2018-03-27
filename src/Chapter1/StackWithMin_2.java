package Chapter1;

import java.util.Stack;

/**
 * 实现一个能够获取最小值的栈结构，时间复杂度的O(1)
 * 两个栈，一个正常保存数据，另一个保存最小值。
 * 方法2:
 * push方法:dataStack正常入栈，若minStack为空，或数据 小于等于 minStack的栈顶数据，则minStack入栈该数据，
 *                          若数据 大于 minStack的栈顶数据，则minStack入栈其栈顶数据（即重复其栈顶数据)
 * pop方法:dataStack。minStack均正常出栈
 * getMin方法:返回minStack的栈顶数据
 * 注意:pop和getMin方法中，栈为空的异常
 */
public class StackWithMin_2 {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public StackWithMin_2(){
        this.dataStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(Integer data){
        if(minStack.empty() || data <= this.getMin()){
            minStack.push(data);
        }
        else if(this.getMin() < data){
            minStack.push(minStack.peek());
        }
        dataStack.push(data);
    }

    public Integer pop(){
        if(dataStack.empty() || minStack.empty()){
            throw new RuntimeException();
        }
        minStack.pop();
        return dataStack.pop();
    }

    public Integer getMin(){
        if(minStack.empty()){
            throw new RuntimeException();
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        StackWithMin_2 testStack = new StackWithMin_2();
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
