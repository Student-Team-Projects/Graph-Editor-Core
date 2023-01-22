package graph_editor.properties;

import graph_editor.graph.GraphElement;

public interface PropertyRepository {
    GraphProperty getPropertyByName(String name);
    Iterable<String> getPropertiesNames();

    default Iterable<GraphElement> getElementsWithProperty(GraphProperty property) {
        return property.graphElementsWithProperty();
    }
}
