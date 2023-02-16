package jni;

public class Tools {
    static {
        if (!LibraryLoader.load(Tools.class, "tools"))
            System.loadLibrary("tools");
    }
    public String foo() {
        return "foo";
    }
    public native String bar();
    public native int[] dave(int n);
    public native double[] arrange(int n, int m, double[] x, double[] y, int[] tab_edge_source, int[] tab_edge_target);
}