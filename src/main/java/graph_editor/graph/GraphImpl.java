package graph_editor.graph;

import java.util.*;
import java.util.stream.Collectors;

class GraphImpl implements Graph {
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
        return vertices;
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

    private List<Edge> edges;
    private List<Vertex> vertices;
}