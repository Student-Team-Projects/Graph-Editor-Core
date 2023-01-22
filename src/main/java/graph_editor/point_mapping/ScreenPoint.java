package graph_editor.point_mapping;

public class ScreenPoint {
    private final int x;
    private final int y;

    public ScreenPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}
