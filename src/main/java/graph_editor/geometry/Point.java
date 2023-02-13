package graph_editor.geometry;

// Immutable.
public class Point implements Geometry {
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    private double x;
    private double y;
}