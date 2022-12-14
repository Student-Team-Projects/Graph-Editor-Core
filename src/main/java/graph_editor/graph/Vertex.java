package graph_editor.graph;

import java.util.List;

// Immutable outside package.
public interface Vertex {
    int getIndex();
    List<Edge> getEdges();
    List<Vertex> getAdjacent();
}