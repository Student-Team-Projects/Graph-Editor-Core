package graph_editor.visual;

import graph_editor.geometry.Point;
import graph_editor.graph.*;

import java.util.Map;

// Immutable
public interface GraphVisualization<T extends Graph> {
    Point getVertexPoint(Vertex vertex);
    T getGraph();
    Map<Vertex, Point> getVisualization();
}