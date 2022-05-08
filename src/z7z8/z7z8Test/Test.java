package z7z8.z7z8Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cash
 * @description
 * @date 2022/4/17 2:21 PM
 */
public class Test {
    Map<Integer, Integer> memory = new HashMap() {{
        put(0, 1);
    }};

    public int numTrees(int n) {
        if (memory.containsKey(n)) {
            return memory.get(n);
        }
        int sum = 0;
        //G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0),计算有重叠,要存放计算结果
        //种类只与节点个数有关
        for (int i = 1; i <= n; i++) {
            sum += numTrees(i - 1) * numTrees(n - i);
        }
        memory.put(n, sum);
        return memory.get(n);
    }
}
