package graph_editor.visual;

import graph_editor.graph.*;

import java.io.*;
import java.util.*;

import graph_editor.geometry.Point;

// Only mutable in this package.
class GraphVisualizationImpl<T extends Graph> implements GraphVisualization<T>, Serializable {
    GraphVisualizationImpl(T graph) {
        vertex_coord = new HashMap<Vertex, Point>();
        this.graph = graph;
    }

    void setCoord(Vertex vertex, Point point) {
        vertex_coord.put(vertex, point);
    }

    @Override
    public Point getVertexPoint(Vertex vertex) {
        return vertex_coord.get(vertex);
    }

    @Override
    public T getGraph() {
        return graph;
    }

    @Override
    public Map<Vertex, Point> getVisualization() {
        return vertex_coord;
    }

    private Map<Vertex, Point> vertex_coord; 
    private T graph;

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeLong(serialVersionUID);
        oos.writeObject(graph);

        var entries = vertex_coord.entrySet();
        oos.writeInt(entries.size());
        for (var entry : entries) {
            oos.writeInt(entry.getKey().getIndex());
            Point p = entry.getValue();
            oos.writeDouble(p.getX());
            oos.writeDouble(p.getY());
        }
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        long serialUID = ois.readLong();
        if (serialUID != serialVersionUID) {
            throw new IOException("Incorrect serialization version: " + serialUID + ", expected " + serialVersionUID);
        }
        graph = (T) ois.readObject();
        int size = ois.readInt();
        vertex_coord = new HashMap<>();
        var vertices = graph.getVertices();
        for (int  i = 0; i < size; i++) {
            int index = ois.readInt();
            Point p = new Point(ois.readDouble(), ois.readDouble());
            vertex_coord.put(vertices.get(index), p);
        }
    }
    private static final long serialVersionUID = 2L;
}