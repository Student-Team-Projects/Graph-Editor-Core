package graph_editor.graph;

import java.util.*;
import java.io.*;

public class DirectedGraph implements Graph, Serializable {
    @Serial
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

    public static class DirectedGraphBuilder implements GenericGraphBuilder<DirectedGraph> {
        private final List<Edge> edges = new ArrayList<>();
        private final List<VertexImpl> vertices = new ArrayList<>();
        private int currentIndex = 0;

        public DirectedGraphBuilder() {}

        public DirectedGraphBuilder(int vertexCount) {
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
        public void addEdge(int sourceIndex, int targetIndex) {
            Edge edge = new EdgeImpl(vertices.get(sourceIndex), vertices.get(targetIndex));
            vertices.get(sourceIndex).getEdges().add(edge);
            edges.add(edge);
        }

        @Override
        public DirectedGraph build() {
            return new DirectedGraph(edges, vertices);
        }
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeLong(serialVersionUID);
        oos.writeInt(vertices.size());
        oos.writeInt(edges.size());
        for (Edge e : edges) {
            oos.writeInt(e.getSource().getIndex());
            oos.writeInt(e.getTarget().getIndex());
        }
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        long serialUID = ois.readLong();
        if (serialUID != serialVersionUID) {
            throw new IOException("This is an older serialization version.");
        }

        int vertexCount = ois.readInt();
        DirectedGraphBuilder builder = new DirectedGraphBuilder(vertexCount);
        int num_edges = ois.readInt();
        for (int i = 0; i < num_edges; i++) {
            builder.addEdge(ois.readInt(), ois.readInt());
        }
        DirectedGraph graph = builder.build();
        vertices = graph.getVertices();
        edges = graph.getEdges();
    }
}