package graph_editor.draw;

import graph_editor.graph.Graph;
import graph_editor.visual.*;
import graph_editor.geometry.Geometry;
import java.util.List;

public interface GraphDrawer<T extends Graph> {
    void drawGraph(GraphVisualization<T> visual);
    List<Geometry> getPrimitiveGrometries(GraphVisualization<T> visual);
}