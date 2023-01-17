package graph_editor.properties;

import graph_editor.graph.Edge;
import graph_editor.graph.Graph;
import graph_editor.graph.Vertex;

import java.util.List;
import java.util.Map;

public class PropertySupportingGraph implements Graph, PropertyRepository {
    private final Graph properGraph;
    private final Map<String, GraphProperty> properties;

    public PropertySupportingGraph(Graph properGraph, Map<String, GraphProperty> properties) {
        this.properGraph = properGraph;
        this.properties = properties;
    }

    @Override
    public List<Edge> getEdges() {
        return properGraph.getEdges();
    }

    @Override
    public List<Vertex> getVertices() {
        return properGraph.getVertices();
    }

    @Override
    public GraphProperty getPropertyByName(String name) {
        return properties.get(name);
    }
}
