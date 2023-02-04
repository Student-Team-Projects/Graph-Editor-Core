package graph_editor.properties;

import graph_editor.graph.*;

import java.io.*;
import java.util.*;

public class PropertySupportingGraphImpl implements PropertySupportingGraph, Serializable {
    private static final long serialVersionUID = 3L;
    private Graph properGraph;
    private Map<String, GraphProperty> properties;
    private final Map<Integer, ExtendedGraphElement> extendedGraphElements = new HashMap<>();
    private final Map<ExtendedGraphElement, Integer> inverseMap = new HashMap<>();
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
    public Iterable<GraphElement> getElementsWithProperty(String propertyName) {
        return properties.get(propertyName).graphElementsWithProperty();
    }

    @Override
    public String getPropertyValue(String propertyName, GraphElement element) {
        return properties.get(propertyName).getElementProperty(element);
    }

    @Override
    public String toString() {
        return properGraph.toString() +
                "\n" +
                properties.toString() +
                "\n";
    }

    private enum Tag {
        vertexTag,
        edgeTag,
        extendedTag
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeLong(serialVersionUID);
        System.out.println("proper saving");
        oos.writeObject(properGraph);
        System.out.println("proper saved");
        oos.writeInt(extendedGraphElements.size());
        for (var entry : extendedGraphElements.entrySet()) {
            oos.writeInt(entry.getKey());
            oos.writeObject(entry.getValue());
        }
        System.out.println("extended saved");
        oos.writeInt(properties.values().size());
        System.out.println("saving properties");
        for (GraphProperty property : properties.values()) {
            System.out.println("property: " + property.getName());
            oos.writeObject(property.getName());
            var entries = property.getEntriesWithProperty();
            oos.writeInt(entries.size());
            for (var entry : entries) {
                GraphElement element = entry.getKey();
                if (element instanceof Vertex v) {
                    oos.writeObject(Tag.vertexTag);
                    oos.writeInt(v.getIndex());
                } else if (element instanceof Edge e) {
                    oos.writeObject(Tag.edgeTag);
                    oos.writeInt(e.getSource().getIndex());
                    oos.writeInt(e.getTarget().getIndex());
                } else if (element instanceof ExtendedGraphElement) {
                    oos.writeObject(Tag.extendedTag);
                    oos.writeInt(inverseMap.get(element));
                } else {
                    throw new IOException(element + " is not serializable");
                }
                oos.writeObject(entry.getValue());
            }
        }
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        long serialUID = ois.readLong();
        if (serialUID != serialVersionUID) {
            throw new IOException("Incorrect serialization version: " + serialUID + ", expected " + serialVersionUID);
        }
        properGraph = (Graph) ois.readObject();
        int extendedElementsNumber = ois.readInt();
        for (int i = 0; i < extendedElementsNumber; i++) {
            int elementId = ois.readInt();
            var element = (ExtendedGraphElement) ois.readObject();
            extendedGraphElements.put(elementId, element);
            inverseMap.put(element, elementId);
            if (extendedId <= elementId) { extendedId = elementId + 1; }
        }

        properties = new HashMap<>();
        int propertiesNumber = ois.readInt();
        for (int i = 0; i < propertiesNumber; i++) {
            String name = (String) ois.readObject();
            GraphProperty property = new GraphProperty(name);
            int elementsWithProperty = ois.readInt();
            for (int j = 0; j < elementsWithProperty; j++) {
                Tag tag = (Tag) ois.readObject();
                GraphElement element;
                if (tag.equals(Tag.vertexTag)) {
                    int index = ois.readInt();
                    element = properGraph.getVertices().get(index);
                } else if (tag.equals(Tag.edgeTag)) {
                    int sourceIndex = ois.readInt();
                    int targetIndex = ois.readInt();
                    element = properGraph.getVertices().get(sourceIndex).getEdges().get(targetIndex);
                } else if (tag.equals(Tag.extendedTag)) {
                    element = extendedGraphElements.get(ois.readInt());
                } else {
                    throw new IOException("unexpected type of GraphElement");
                }
                String value = (String) ois.readObject();
                property.addElementProperty(element, value);
            }
        }
    }
}
