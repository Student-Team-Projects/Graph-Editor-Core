package graph_editor.properties;

import graph_editor.graph.ExtendedGraphElement;
import graph_editor.graph.Graph;
import graph_editor.graph.GraphElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyGraphBuilder<G extends Graph> {
    private final Map<String, GraphProperty> properties = new HashMap<>();
    private final List<ExtendedGraphElement> extendedGraphElements = new ArrayList<>();
    private final G graph;

    public PropertyGraphBuilder(G properGraph) {
        this.graph = properGraph;
    }

    public void registerProperty(String property) {
        properties.put(property, new GraphProperty(property));
    }

    public void addExtendedElement(ExtendedGraphElement element) { extendedGraphElements.add(element); }

    public void setElementProperty(GraphElement element, String propertyName, String value) {
        if (!properties.containsKey(propertyName)) {
            registerProperty(propertyName);
        }
        properties.get(propertyName).addElementProperty(element, value);
    }

    public PropertySupportingGraph build() {
        return new PropertySupportingGraphImpl<>(graph, properties, extendedGraphElements);
    }
}
