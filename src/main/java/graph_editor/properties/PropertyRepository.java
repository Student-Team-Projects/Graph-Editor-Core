package graph_editor.properties;

import graph_editor.graph.Edge;
import graph_editor.graph.GraphElement;
import graph_editor.graph.Vertex;

public interface PropertyRepository {
    Iterable<String> getPropertiesNames();
    Iterable<Vertex> getVerticesWithProperty(String propertyName);
    Iterable<Edge> getEdgesWithProperty(String propertyName);
    String getPropertyValue(String propertyName, Vertex element);
    String getPropertyValue(String propertyName, Edge element);
}
