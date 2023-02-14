package graph_editor.graph;

public interface GenericGraphBuilder<G extends Graph> {
    Vertex addVertex();
    void addEdge(int sourceIndex, int targetIndex);
    G build();
}
