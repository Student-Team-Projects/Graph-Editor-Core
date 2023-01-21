package graph_editor.graph;

import java.util.ArrayList;

public class SimpleGraphBuilder implements GraphBuilder {
    private final ArrayList<VertexImpl> vertices;
    public SimpleGraphBuilder(int vertex_count) {
        vertices = new ArrayList<>();
        for (int i = 0; i < vertex_count; i++) {
            vertices.add(new VertexImpl(i));
        }
    }

    public void addVertex() {
        VertexImpl vertex = new VertexImpl(vertices.size());
        vertices.add(vertex);
//        return vertex;
    }

    public void addEdge(int source_index, int target_index) {
        if (source_index >= vertices.size() || target_index >= vertices.size()) {
            throw new IllegalArgumentException("Node index out of bounds.");
        }

        Edge edge = new EdgeImpl(vertices.get(source_index), vertices.get(target_index));
        vertices.get(source_index).addEdge(edge);
    }

    public void addBidirectionalEdge(int source_index, int target_index) {
        addEdge(source_index, target_index);
        if (source_index != target_index) {
            addEdge(target_index, source_index);
        }
    }

    public Graph build() {
        return new GraphImpl(new ArrayList<>(vertices
                .stream()
                .map(VertexImpl::new)
                .toList()));
    }
}
