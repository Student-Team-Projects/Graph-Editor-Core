package graph_editor.properties;

import graph_editor.graph.*;

import java.io.*;
import java.util.*;

public class PropertySupportingGraphImpl implements PropertySupportingGraph, Serializable {
    private static final long serialVersionUID = 3L;
    private Graph properGraph;
    private Map<String, GraphProperty> properties;
    private Map<Integer, ExtendedGraphElement> extendedGraphElements = new HashMap<>();
    private Map<ExtendedGraphElement, Integer> inverseMap = new HashMap<>();
    private int extendedId = 0;

    PropertySupportingGraphImpl(Graph properGraph, Map<String, GraphProperty> properties, Iterable<ExtendedGraphElement> extendedGraphElements) {
        this.properGraph = properGraph;
        this.properties = properties;
        extendedGraphElements.forEach(element -> {
            this.extendedGraphElements.put(extendedId, element);
            this.inverseMap.put(element, extendedId);
            extendedId++;
        });
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
    public Collection<ExtendedGraphElement> getExtendedElements() {
        return Collections.unmodifiableCollection(extendedGraphElements.values());
    }

    @Override
    public Iterable<String> getPropertiesNames() {
        return properties.keySet();
    }

    @Override
    public Iterable<Vertex> getVerticesWithProperty(String propertyName) {
        GraphProperty property = properties.get(propertyName);
        return property != null ? property.graphVerticesWithProperty() : Collections.emptyList();
    }

    @Override
    public Iterable<Edge> getEdgesWithProperty(String propertyName) {
        GraphProperty property = properties.get(propertyName);
        return property != null ? property.graphEdgesWithProperty() : Collections.emptyList();
    }

    @Override
    public String getPropertyValue(String propertyName, Vertex element) {
        GraphProperty property = properties.get(propertyName);
        return property != null ? properties.get(propertyName).getElementProperty(element) : null;
    }

    @Override
    public String getPropertyValue(String propertyName, Edge element) {
        GraphProperty property = properties.get(propertyName);
        return property != null ? properties.get(propertyName).getElementProperty(element) : null;
    }

    @Override
    public String toString() {
        return properGraph.toString() +
                "\n" +
                properties.toString() +
                "\n";
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeLong(serialVersionUID);
        oos.writeObject(properGraph);
        oos.writeInt(extendedGraphElements.size());
        for (Map.Entry<Integer, ExtendedGraphElement> entry : extendedGraphElements.entrySet()) {
            oos.writeInt(entry.getKey());
            oos.writeObject(entry.getValue());
        }
        oos.writeInt(properties.values().size());
        for (GraphProperty property : properties.values()) {
            oos.writeObject(property.getName());
            Set<Map.Entry<Vertex, String>> vEntries = property.getVertexEntriesWithProperty();
            oos.writeInt(vEntries.size());
            for (Map.Entry<Vertex, String> entry : vEntries) {
                Vertex element = entry.getKey();
                oos.writeInt(element.getIndex());
                oos.writeObject(entry.getValue());
            }
            Set<Map.Entry<Edge, String>> eEntries = property.getEdgeEntriesWithProperty();
            oos.writeInt(eEntries.size());
            for (Map.Entry<Edge, String> entry : eEntries) {
                Edge element = entry.getKey();
                oos.writeInt(element.getSource().getIndex());
                oos.writeInt(element.getTarget().getIndex());
                oos.writeObject(entry.getValue());
            }
        }
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        extendedGraphElements = new HashMap<>();
        inverseMap = new HashMap<>();

        long serialUID = ois.readLong();
        if (serialUID != serialVersionUID) {
            throw new IOException("Incorrect serialization version: " + serialUID + ", expected " + serialVersionUID);
        }
        properGraph = (Graph) ois.readObject();
        int extendedElementsNumber = ois.readInt();
        for (int i = 0; i < extendedElementsNumber; i++) {
            int elementId = ois.readInt();
            ExtendedGraphElement element = (ExtendedGraphElement) ois.readObject();
            extendedGraphElements.put(elementId, element);
            inverseMap.put(element, elementId);
            if (extendedId <= elementId) { extendedId = elementId + 1; }
        }

        properties = new HashMap<>();
        int propertiesNumber = ois.readInt();
        for (int i = 0; i < propertiesNumber; i++) {
            String name = (String) ois.readObject();
            GraphProperty property = new GraphProperty(name);
            properties.put(name, property);
            int verticesWithProperty = ois.readInt();
            for (int j = 0; j < verticesWithProperty; j++) {
                int index = ois.readInt();
                Vertex element = properGraph.getVertices().get(index);
                String value = (String) ois.readObject();
                property.addElementProperty(element, value);
            }
            int edgesWithProperty = ois.readInt();
            for (int j = 0; j < edgesWithProperty; j++) {
                int sourceIndex = ois.readInt();
                int targetIndex = ois.readInt();
                List<Edge> edges = properGraph.getVertices().get(sourceIndex).getEdges();
                Edge element = edges.stream().filter(e -> e.getSource().getIndex() == targetIndex || e.getTarget().getIndex() == targetIndex).findFirst().get();
                String value = (String) ois.readObject();
                property.addElementProperty(element, value);
            }
        }
    }
}
