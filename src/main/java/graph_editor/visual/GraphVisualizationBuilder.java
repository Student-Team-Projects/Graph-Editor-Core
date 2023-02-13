package graph_editor.visual;

import graph_editor.geometry.Point;
import graph_editor.graph.Vertex;

public interface GraphVisualizationBuilder {
    void addCoordinates(Vertex v, Point p);
}
