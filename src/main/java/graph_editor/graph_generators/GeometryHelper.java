package graph_editor.graph_generators;

import graph_editor.geometry.Point;

public class GeometryHelper {
    private GeometryHelper() {}

    public static Point getPointOnCycle(int index, int pointsOnCycle) {
        double angle = (2*Math.PI*((double) index)) / (double) pointsOnCycle;
        double x = 0.5 + 0.5 * Math.sin(angle);
        double y = 0.5 + 0.5 * Math.cos(angle);
        return new Point(x, y);
    }
}
