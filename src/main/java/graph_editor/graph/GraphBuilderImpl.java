package graph_editor.graph;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.stream.Collectors;

public class GraphBuilderImpl implements GraphBuilder {
    public GraphBuilderImpl(int vertex_count) {
        graph_versions = new ArrayList<>();

        vertices = new ArrayList<VertexImpl>();
        for (int i = 0; i < vertex_count; i++) {
            vertices.add(new VertexImpl(i));
        }
        cur_version = -1;
        build();
    }

    public void addVertex() {
        unwindChangeStack();
        vertices.add(new VertexImpl(vertices.size()));
    }

    public void addEdge(int source_index, int target_index) {
        if (source_index >= vertices.size() || target_index >= vertices.size()) {
            throw new IllegalArgumentException("Node index out of bounds.");
        }
        unwindChangeStack();

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
        unwindChangeStack();
        Graph graph = new GraphImpl(new ArrayList<Vertex>(vertices
            .stream()
            .map(v -> new VertexImpl(v))
            .map(v -> (Vertex)v)
            .toList()));
        graph_versions.add(graph);
        cur_version = graph_versions.size() - 1;
        return graph;
    }

    public static Graph readFromStream(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        GraphImpl graph = new GraphImpl(new ArrayList<>());
        graph.readObject(ois);
        return graph;
    }

    public static void writeToStream(Graph graph, ObjectOutputStream oos) throws ClassNotFoundException, IOException {
        ((GraphImpl)graph).writeObject(oos);
    }

    public void goToPrevBuild() {
        if (cur_version > 0) {
            cur_version--;
        }
    }

    public void undoGoingToPrevBuild() {
        if (cur_version + 1 < graph_versions.size()) {
            cur_version++;
        }
    }

    private void unwindChangeStack() {
        if (cur_version == graph_versions.size() - 1) {
            return;
        }

        while(graph_versions.size() - 1 > cur_version) {
            graph_versions.remove(graph_versions.size() - 1);
        }
        takeVerticesFromCurrentVersion();
    }

    private void takeVerticesFromCurrentVersion() {
        vertices = new ArrayList<>();
        vertices.addAll(graph_versions.get(cur_version).getVertices()
            .stream()
            .map(vertex -> new VertexImpl(vertex))
            .collect(Collectors.toList()));
    }

    private int cur_version = 0;
    private List<Graph> graph_versions;
    private ArrayList<VertexImpl> vertices;
}