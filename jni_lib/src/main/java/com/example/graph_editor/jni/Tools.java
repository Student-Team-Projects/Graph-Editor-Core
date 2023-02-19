package com.example.graph_editor.jni;

public class Tools {
    static {
        if (!LibraryLoader.load(Tools.class, "tools")) {
            System.loadLibrary("tools");
        }
    }
    public native double[] arrange(int n, int m, double[] x, double[] y, int[] tab_edge_source, int[] tab_edge_target);
    public native double[] arrangePlanar(int n, int m, double[] x, double[] y, int[] tab_edge_source, int[] tab_edge_target);
    public native double[] makePlanar(int n, int m, double[] x, double[] y, int[] tab_edge_source, int[] tab_edge_target);
}