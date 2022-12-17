package graph_editor.draw;

import graph_editor.visual.*;
import graph_editor.geometry.Geometry;
import java.util.List;

public interface GraphDrawer {
    public void drawGraph(GraphVisualization visual);
    public List<Geometry> getPrimitiveGrometries(GraphVisualization visual);
}