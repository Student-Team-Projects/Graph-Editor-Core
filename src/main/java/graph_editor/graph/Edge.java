package graph_editor.graph;

import java.util.List;

// Immutable outside package.
public interface Edge extends GraphElement {
    Vertex getSource();
    Vertex getTarget();
}