package graph_editor.properties;

import graph_editor.graph.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertySupportingGraphImpl implements PropertySupportingGraph, Serializable {
    private static final long serialVersionUID = 2L;
    private Graph properGraph;
    private Map<String, GraphProperty> properties;

    PropertySupportingGraphImpl(Graph properGraph, Map<String, GraphProperty> properties) {
        this.properGraph = properGraph;
        this.properties = properties;
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
    public GraphProperty getPropertyByName(String name) {
        return properties.get(name);
    }

    @Override
    public Iterable<String> getPropertiesNames() {
        return properties.keySet();
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
    //TODO consider implementing serializable in Vertex, Edge
    void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeLong(serialVersionUID);
        oos.writeObject(properGraph);
        oos.writeInt(properties.values().size());
        for (GraphProperty property : properties.values()) {
            oos.writeObject(property.getName());
            var entries = property.getElementsWithProperty();
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
                    oos.writeObject(element);
                } else {
                    throw new IOException(element + "not serializable");
                }
                oos.writeObject(entry.getValue());
            }
        }
    }
    //TODO consider implementing serializable in Vertex, Edge
    void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        long serialUID = ois.readLong();
        if (serialUID != serialVersionUID) {
            throw new IOException("Incorrect serialization version: " + serialUID + ", expected " + serialVersionUID);
        }
        properGraph = (Graph) ois.readObject();
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
                } else if(tag.equals(Tag.extendedTag)) {
                    element = (GraphElement) ois.readObject();
                } else {
                    throw new IOException("unexpected type of GraphElement");
                }
                String value = (String) ois.readObject();
                property.addElementProperty(element, value);
            }
        }
    }
}
