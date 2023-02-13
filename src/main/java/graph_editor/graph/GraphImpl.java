package graph_editor.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class GraphImpl implements Graph, Serializable {
    private static final long serialVersionUID = 1L;

    GraphImpl(List<Vertex> vertices) {
        this.vertices = new ArrayList<>();
        this.vertices.addAll(vertices);
        edges = vertices.stream()
            .map(vertex -> vertex.getEdges())
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    } 

    @Override
    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public List<Vertex> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Graph)) {
            return false;
        }
        Graph other = (Graph)o;
        if (getVertices().size() != other.getVertices().size()) {
            return false;
        }
        if (other.getEdges().size() != getEdges().size()) {
            return false;
        }
        if (getEdges().stream()
            .filter(edge -> !other.getEdges().contains(edge))
            .findAny()
            .isPresent()) 
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(vertices.size());
        builder.append("\n");
        builder.append(edges.size());
        builder.append("\n");
        for (Edge e : edges) {
            builder.append(e.getSource().getIndex() + " " + e.getTarget().getIndex() + "\n");
        }
        return builder.toString();
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

        int num_vertices = ois.readInt();
        GraphBuilderImpl builder = new GraphBuilderImpl(num_vertices);
        int num_edges = ois.readInt();
        for (int i = 0; i < num_edges; i++) {
            builder.addEdge(ois.readInt(), ois.readInt());
        }
        Graph graph = builder.build();
        vertices = graph.getVertices();
        edges = graph.getEdges();
    }

    private List<Edge> edges;
    private List<Vertex> vertices;
}