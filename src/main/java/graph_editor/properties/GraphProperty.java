package graph_editor.properties;

import graph_editor.graph.Edge;
import graph_editor.graph.Vertex;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class GraphProperty {
    private final String name;
    private Map<Vertex, String> verticesValues = new HashMap<>();
    private Map<Edge, String> edgesValues = new HashMap<>();

    GraphProperty(String name) {
        this.name = name;
    }

    String getElementProperty(Vertex element) {
        return verticesValues.get(element);
    }
    String getElementProperty(Edge e) {
        return edgesValues.get(e);
    }
    String getName() {
        return name;
    }

    void addElementProperty(Vertex element, String value) {
        verticesValues.put(element, value);
    }
    void addElementProperty(Edge element, String value) {
        edgesValues.put(element, value);
    }

    Collection<Vertex> graphVerticesWithProperty() {
        return verticesValues.keySet();
    }
    Collection<Edge> graphEdgesWithProperty() { return edgesValues.keySet(); }
    Set<Map.Entry<Edge, String>> getEdgeEntriesWithProperty() {
        return edgesValues.entrySet();
    }
    Set<Map.Entry<Vertex, String>> getVertexEntriesWithProperty() {
        return verticesValues.entrySet();
    }
}
