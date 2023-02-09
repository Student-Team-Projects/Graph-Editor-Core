package graph_editor.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class UndirectedGraph implements Graph, Serializable {
    private final List<Edge> edges;
    private final List<? extends Vertex> vertices;

    private UndirectedGraph(List<Edge> edges, List<VertexImpl> vertices) {
        this.edges = edges;
        this.vertices = vertices;
    }

    @Override
    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    @Override
    public List<Vertex> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(vertices.size());
        builder.append("\n");
        builder.append(edges.size());
        builder.append("\n");
        for (Edge e : edges) {
            int sourceIndex = e.getSource().getIndex();
            int targetIndex = e.getTarget().getIndex();
            if (sourceIndex < targetIndex) {
                builder.append(sourceIndex);
                builder.append(" ");
                builder.append(targetIndex);
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public static class UndirectedGraphBuilder implements GenericGraphBuilder<UndirectedGraph> {
        private final List<Edge> edges = new ArrayList<>();
        private final List<VertexImpl> vertices = new ArrayList<>();
        private int currentIndex = 0;
        @Override
        public Vertex addVertex() {
            VertexImpl vertex = new VertexImpl(currentIndex);
            currentIndex++;
            vertices.add(vertex);
            return vertex;
        }

        @Override
        public void addEdge(int sourceIndex, int targetIndex) {
            addDirectedEdge(sourceIndex, targetIndex);
            addDirectedEdge(targetIndex, sourceIndex);
        }

        private void addDirectedEdge(int sourceIndex, int targetIndex) {
            Edge edge = new EdgeImpl(vertices.get(sourceIndex), vertices.get(targetIndex));
            vertices.get(sourceIndex).getEdges().add(edge);
            edges.add(edge);
        }

        @Override
        public UndirectedGraph build() {
            return new UndirectedGraph(edges, vertices);
        }
    }
}