package jni;

import com.example.graph_editor.jni.Tools;
import org.junit.jupiter.api.Test;

class ToolsTest {
    @Test
    void test() {
        Tools tools = new Tools();
        double[] x = {0, 1, 2};
        double[] y = {0, 1, 2};
        int[] arris = {0, 1};
        int[] arrit = {1, 2};
        tools.arrange(3, 2, x, y, arris, arrit);
        tools.arrangePlanar(3, 2, x, y, arris, arrit);
        double[] planarEmbedding = tools.makePlanar(3, 2, x, y, arris, arrit);
        System.out.println(planarEmbedding[0] + " " + planarEmbedding[1]);
    }

}