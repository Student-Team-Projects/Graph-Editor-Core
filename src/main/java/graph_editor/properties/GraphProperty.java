package graph_editor.properties;

import graph_editor.graph.Graph;
import graph_editor.graph.GraphElement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GraphProperty {
    private final String name;
    private Map<GraphElement, String> values = new HashMap<>();

    public GraphProperty(String name) {
        this.name = name;
    }

    public String getElementProperty(GraphElement element) {
        return values.get(element);
    }
    String getName() {
        return name;
    }

    void addElementProperty(GraphElement element, String value) {
        values.put(element, value);
    }
}
