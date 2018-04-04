package Chapter1;

import java.util.Arrays;
import java.util.Stack;

/** Chapter1 Q9 求最大子矩阵的大小
 *  将求矩阵大小, 转换成求以某一行为底的最大矩形面积.
 * Created by haohao on 2018/3/31.
 */
public class MaxRecSize {
    public static int maxRexSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                height[j] = (map[i][j] == 0 ? 0 : height[j] + 1);
            }
            int maxAreaFromBottom = maxRecFromBottom(height);
            if (maxAreaFromBottom > maxArea) maxArea = maxAreaFromBottom;
        }
        return maxArea;
    }

    /**
     * 求以height为高度的区域, 最大能切割出的矩形
     *
     * @param height
     * @return
     */
    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                if (curArea > maxArea) maxArea = curArea;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            if (curArea > maxArea) maxArea = curArea;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = new int[]{3, 4, 5, 4, 3, 6};
        System.out.println(maxRecFromBottom(height));

        int[][] map = new int[][]{
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        System.out.println(maxRexSize(map));
    }
}
