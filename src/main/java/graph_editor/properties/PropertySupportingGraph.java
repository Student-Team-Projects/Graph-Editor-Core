package graph_editor.properties;

import graph_editor.graph.Graph;

import java.util.Collection;

public interface PropertySupportingGraph extends Graph, PropertyRepository {
    Collection<ExtendedGraphElement> getExtendedElements();
}
