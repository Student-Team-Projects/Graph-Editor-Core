package graph_editor.properties;

import graph_editor.graph.ExtendedGraphElement;
import graph_editor.graph.Graph;
import graph_editor.graph.GraphElement;
import graph_editor.graph.SimpleGraphBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyGraphBuilder extends SimpleGraphBuilder {
    private final Map<String, GraphProperty> properties = new HashMap<>();
    private final List<ExtendedGraphElement> extendedGraphElements = new ArrayList<>();
    public PropertyGraphBuilder(int vertex_count) {
        super(vertex_count);
    }

    public void registerProperty(GraphProperty property) {
        properties.put(property.getName(), property);
    }

    public void addExtendedElement(ExtendedGraphElement element) { extendedGraphElements.add(element); }

    public void addElementProperty(GraphElement element, GraphProperty property, String value) {
        property.addElementProperty(element, value);
    }
    @Override
    public PropertySupportingGraph build() {
        Graph proper = super.build();
        return new PropertySupportingGraphImpl(proper, properties, extendedGraphElements);
    }
}
