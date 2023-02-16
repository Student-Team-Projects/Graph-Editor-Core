package jni;

import com.example.graph_editor.jni.Tools;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToolsTest {

    @Test
    void testFooBar() {
        Tools tools = new Tools();
        assertEquals(tools.foo(), "foo");
        assertEquals(tools.bar(), "bar");
        assertEquals(tools.dave(3)[0], 6);
        double[] x = {0, 1};
        double[] y = {0, 1};
        int[] arris = {0};
        int[] arrit = {1};
        tools.arrange(2, 1, x, y, arris, arrit);
        tools.arrangePlanar(2, 1, x, y, arris, arrit);
        tools.makePlanar(2, 1, x, y, arris, arrit);
    }

}