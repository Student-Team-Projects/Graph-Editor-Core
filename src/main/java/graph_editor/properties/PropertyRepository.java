package graph_editor.properties;

import graph_editor.graph.GraphElement;

public interface PropertyRepository {
    Iterable<String> getPropertiesNames();

    Iterable<GraphElement> getElementsWithProperty(String propertyName);
    String getPropertyValue(String propertyName, GraphElement element);
}
