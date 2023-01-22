package graph_editor.point_mapping;

import graph_editor.geometry.Point;

public interface PointMapper {
    ScreenPoint mapIntoView(Point point);
    Point mapFromView(ScreenPoint screenPoint);
}
