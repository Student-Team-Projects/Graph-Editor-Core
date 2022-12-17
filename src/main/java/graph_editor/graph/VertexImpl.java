package graph_editor.graph;

import java.util.*;

// Only for this package
class VertexImpl implements Vertex {
    VertexImpl(int index) {
        this.index = index;
        edges = new ArrayList<>();
    }

    VertexImpl(Vertex other) {
        this.index = other.getIndex();
        edges = new ArrayList<>();
        edges.addAll(other.getEdges());
    }

    void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public List<Vertex> getAdjacent() {
        return edges.stream().map(edge -> edge.getTarget()).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex v) {
            return v.getIndex() == getIndex();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return index;
    }

    private int index;
    private ArrayList<Edge> edges;
}