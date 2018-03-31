package Chapter1;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**Chapter1 Q7 滑动窗口的最大值
 * 数组arr, 窗口长度w, 求出每个窗口的最大值
 * arr=[4, 3, 5, 4, 3, 3, 6, 7], w=3, 则窗口最大值组成的数组为：[5, 5, 5, 4, 6, 7]
 * 用一个双端队列maxIndexDeque保存"可能的最大值"的index:
 *   1. 遍历arr, 只当 maxIndexDeque 为空 或 arr[i] 大于 maxIndexDeque 队尾元素时, 将i放入maxIndexDeque,
 *      否则将maxIndexDeque队尾元素出队列. 此时, maxIndexDeque中的index对应到的arr[i],是从大到小排序的
 *      表示这一段连续空间中的可能的最大值
 *   2. 若 i 与 maxIndexDeque 的头端元素 之间的元素个数 等于 w , 则说明 maxIndexDeque 头端元素对应的
 *      index 已经超过了 w 的范围, 失效, 将其出队列
 * Created by haohao on 2018/3/31.
 */
public class GetMaxWindow {
    public static int[] getMaxWindow(int[] arr, int w){
        if(arr.length == 0 || w > arr.length || w < 1){
            return null;
        }
        Deque<Integer> maxIndexDeque = new LinkedList<>();
        int[] result = new int[arr.length - w + 1];
        int resultIndex = 0;
        for ( int i = 0; i <arr.length; i++){
            while(!maxIndexDeque.isEmpty() && arr[maxIndexDeque.peekLast()] <= arr[i]){
                maxIndexDeque.pollLast();
            }
            maxIndexDeque.offerLast(i);
            if ((i - maxIndexDeque.peekFirst()) == w){
                maxIndexDeque.pollFirst();
            }
            if ( i >= (w - 1)){
                result[resultIndex++] = arr[maxIndexDeque.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {4, 3, 5, 4, 3, 3, 6, 7};
        int w = 3;
        System.out.println(Arrays.toString(getMaxWindow(arr, w)));
    }
}
