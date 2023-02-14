package graph_editor.draw.point_mapping;

//wrapper for android.canvas etc.
//TODO consider wrapping methods like drawPath, drawBitmap
public interface CanvasDrawer {
    void drawCircle(ScreenPoint point, float radius, int argb);
    void drawLine(ScreenPoint start, ScreenPoint end, int argb);
    void drawPolygon(Iterable<ScreenPoint> vertices, int fillArgb);
}
