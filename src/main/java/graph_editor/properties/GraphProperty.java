package graph_editor.properties;

import graph_editor.graph.GraphElement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class GraphProperty {
    private final String name;
    private Map<GraphElement, String> values = new HashMap<>();

    GraphProperty(String name) {
        this.name = name;
    }

    String getElementProperty(GraphElement element) {
        return values.get(element);
    }
    String getName() {
        return name;
    }

    void addElementProperty(GraphElement element, String value) {
        values.put(element, value);
    }

    Collection<GraphElement> graphElementsWithProperty() {
        return values.keySet();
    }
    Set<Map.Entry<GraphElement, String>> getEntriesWithProperty() {
        return values.entrySet();
    }
}
