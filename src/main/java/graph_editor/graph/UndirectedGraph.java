package graph_editor.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UndirectedGraph implements Graph, Serializable {
    @Serial
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

    public static class UndirectedGraphBuilder implements GenericGraphBuilder<UndirectedGraph> {
        private final List<Edge> edges = new ArrayList<>();
        private final List<VertexImpl> vertices = new ArrayList<>();
        private int currentIndex = 0;

        public UndirectedGraphBuilder() {}

        public UndirectedGraphBuilder(int vertexCount) {
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

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeLong(serialVersionUID);
        oos.writeInt(vertices.size());
        oos.writeInt(edges.size());
        for (Edge e : edges) {
            int sourceIndex = e.getSource().getIndex();
            int targetIndex = e.getTarget().getIndex();
            if (sourceIndex < targetIndex) {
                oos.writeInt(sourceIndex);
                oos.writeInt(targetIndex);
            }
        }
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        long serialUID = ois.readLong();
        if (serialUID != serialVersionUID) {
            throw new IOException("This is an older serialization version.");
        }

        int vertexCount = ois.readInt();
        UndirectedGraphBuilder builder = new UndirectedGraphBuilder(vertexCount);
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