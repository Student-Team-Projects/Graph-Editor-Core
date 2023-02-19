package graph_editor.graph;

import java.util.Optional;

public interface GenericGraphBuilder<G extends Graph> {
    Vertex addVertex();
    Optional<Edge> addEdge(int sourceIndex, int targetIndex);
    G build();
}
