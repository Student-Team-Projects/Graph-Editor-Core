package graph_editor.draw;

import graph_editor.draw.point_mapping.ScreenPoint;

//wrapper for android.canvas etc.
//TODO consider wrapping methods like drawPath, drawBitmap
public interface CanvasDrawer {
    void drawCircle(float cx, float cy, float radius, int argb);
    void drawLine(float startX, float startY, float stopX, float stopY, int argb);
    void drawPolygon(Iterable<ScreenPoint> vertices, int fillArgb);
}
