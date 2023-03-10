package graph_editor.graph;

public interface GraphBuilder {
    Vertex addVertex();
    void addEdge(int source_index, int target_index);
    void addBidirectionalEdge(int source_index, int target_index);
    Graph build();
}
