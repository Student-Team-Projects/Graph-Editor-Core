package graph_editor.graph;

import java.util.*;
import java.io.*;

public class DirectedGraph implements Graph, Serializable {
    private static final long serialVersionUID = 1L;
    private List<Edge> edges;
    private List<? extends Vertex> vertices;

    private DirectedGraph(List<Edge> edges, List<VertexImpl> vertices) {
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
            builder.append(e.getSource().getIndex());
            builder.append(" ");
            builder.append(e.getTarget().getIndex());
            builder.append("\n");
        }
        return builder.toString();
    }

    public static class Builder implements GenericGraphBuilder<DirectedGraph> {
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
            Vertex source = vertices.get(sourceIndex);
            Edge edge = new EdgeImpl(source, vertices.get(targetIndex));
            if (!source.getEdges().contains(edge)) {
                source.getEdges().add(edge);
                edges.add(edge);
                return Optional.of(edge);
            }
            return Optional.empty();
        }

        @Override
        public DirectedGraph build() {
            return new DirectedGraph(edges, vertices);
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeLong(serialVersionUID);
        oos.writeInt(vertices.size());
        oos.writeInt(edges.size());
        for (Edge e : edges) {
            oos.writeInt(e.getSource().getIndex());
            oos.writeInt(e.getTarget().getIndex());
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
            builder.addEdge(ois.readInt(), ois.readInt());
        }
        DirectedGraph graph = builder.build();
        vertices = graph.getVertices();
        edges = graph.getEdges();
    }
}