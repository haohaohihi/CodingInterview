package Chapter1;

import java.util.Stack;

/**Chapter1 Q5 用一个栈实现另一个栈的排序
 *  数据栈为stack, 辅助栈为help
 *  循环操作：
 *  1.stack出栈，保存为current
 *  2.help出栈并压入到stack中，直到help为空或help的栈顶 不大于 current;
 *  3.current插入到help中
 * Created by haohao on 2018/3/29.
 */
public class SortStackByStack {
    public static Stack<Integer> sortStackByStack(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();
        Integer current;
        while(!stack.empty()){
            current = stack.pop();
            while (!help.isEmpty() && help.peek() > current){
                stack.push(help.pop());
            }
            help.push(current);
        }
        return help;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        System.out.println(sortStackByStack(stack));
        stack.push(4);
        stack.push(5);
        stack.push(5);
        stack.push(2);
        stack.push(3);
        stack.push(7);
        System.out.println(sortStackByStack(stack));
    }
}
