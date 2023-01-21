package graph_editor.graph;

public interface GraphBuilder {
    void addVertex();
    void addEdge(int source_index, int target_index);
    void addBidirectionalEdge(int source_index, int target_index);
    Graph build();
}
