package graph_editor.graph;

// Immutable outside package.
public interface Edge extends GraphElement {
    Vertex getSource();
    Vertex getTarget();
    Vertex getOther(Vertex vertex);
}