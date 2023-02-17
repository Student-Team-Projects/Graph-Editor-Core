package graph_editor.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UndirectedGraph implements Graph, Serializable {
    private static final long serialVersionUID = 1L;
    private List<Edge> edges;
    private List<? extends Vertex> vertices;

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

    public static class Builder implements GenericGraphBuilder<UndirectedGraph> {
        private final List<Edge> edges = new ArrayList<>();
        private final List<VertexImpl> vertices = new ArrayList<>();
        private int currentIndex = 0;

        public Builder() {}

        public Builder(int vertexCount) {
            while(vertexCount > 0) {
                addVertex();
                vertexCount--;
            }
        }

        @Override
        public Vertex addVertex() {
            VertexImpl vertex = new VertexImpl(currentIndex);
            currentIndex++;
            vertices.add(vertex);
            return vertex;
        }

        @Override
        public Optional<Edge> addEdge(int sourceIndex, int targetIndex) {
            if (sourceIndex >= vertices.size() || targetIndex >= vertices.size()) {
                throw new IllegalArgumentException("Invalid source or target index");
            }
            if (sourceIndex == targetIndex) {
                return Optional.empty();
            }
            Vertex source = vertices.get(Math.min(sourceIndex, targetIndex));
            Vertex target = vertices.get(Math.max(sourceIndex, targetIndex));
            Edge edge = new EdgeImpl(source, target);
            if (!source.getEdges().contains(edge)) {
                source.getEdges().add(edge);
                target.getEdges().add(edge);
                edges.add(edge);
                return Optional.of(edge);
            }
            return Optional.empty();
        }

        @Override
        public UndirectedGraph build() {
            return new UndirectedGraph(edges, vertices);
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeLong(serialVersionUID);
        oos.writeInt(vertices.size());
        oos.writeInt(edges.size());
        for (Edge e : edges) {
            int sourceIndex = e.getSource().getIndex();
            int targetIndex = e.getTarget().getIndex();
            oos.writeInt(sourceIndex);
            oos.writeInt(targetIndex);
        }
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        long serialUID = ois.readLong();
        if (serialUID != serialVersionUID) {
            throw new IOException("This is an older serialization version.");
        }

        int vertexCount = ois.readInt();
        Builder builder = new Builder(vertexCount);
        int num_edges = ois.readInt();
        for (int i = 0; i < num_edges; i++) {
            int sourceIndex = ois.readInt();
            int targetIndex = ois.readInt();
            if (sourceIndex >= targetIndex) {
                throw new IOException("Invalid serialization input");
            }
            builder.addEdge(sourceIndex, targetIndex);
        }
        UndirectedGraph graph = builder.build();
        vertices = graph.getVertices();
        edges = graph.getEdges();
    }
}