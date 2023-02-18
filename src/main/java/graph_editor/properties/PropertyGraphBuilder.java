package graph_editor.properties;

import graph_editor.graph.*;

import java.util.*;

public class PropertyGraphBuilder implements GenericGraphBuilder<PropertySupportingGraph> {
    private final Map<String, GraphProperty> properties = new HashMap<>();
    private final List<ExtendedGraphElement> extendedGraphElements = new ArrayList<>();
    private final GenericGraphBuilder<?> properBuilder;

    public PropertyGraphBuilder(GenericGraphBuilder<?> properBuilder) {
        this.properBuilder = properBuilder;
    }

    public void registerProperty(String property) {
        properties.put(property, new GraphProperty(property));
    }

    public void addExtendedElement(ExtendedGraphElement element) { extendedGraphElements.add(element); }

    public void addElementProperty(Vertex element, String propertyName, String value) {
        if (!properties.containsKey(propertyName)) {
            registerProperty(propertyName);
        }
        properties.get(propertyName).addElementProperty(element, value);
    }
    public void addElementProperty(Edge element, String propertyName, String value) {
        if (!properties.containsKey(propertyName)) {
            registerProperty(propertyName);
        }
        properties.get(propertyName).addElementProperty(element, value);
    }

    @Override
    public Vertex addVertex() {
        return properBuilder.addVertex();
    }

    @Override
    public Optional<Edge> addEdge(int sourceIndex, int targetIndex) {
        return properBuilder.addEdge(sourceIndex, targetIndex);
    }

    @Override
    public PropertySupportingGraph build() {
        return new PropertySupportingGraphImpl(properBuilder.build(), properties, extendedGraphElements);
    }
}
