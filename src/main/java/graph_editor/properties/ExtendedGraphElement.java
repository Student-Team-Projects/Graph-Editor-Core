package graph_editor.properties;

import graph_editor.draw.point_mapping.CanvasDrawer;
import graph_editor.draw.point_mapping.PointMapper;
import graph_editor.graph.GraphElement;

public interface ExtendedGraphElement extends GraphElement {
    void draw(PointMapper mapper, CanvasDrawer drawer);
}