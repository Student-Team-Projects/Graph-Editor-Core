package graph_editor.extensions;

import graph_editor.graph.VersionStack;
import graph_editor.properties.PropertySupportingGraph;
import graph_editor.visual.GraphVisualization;

public interface StackCapture {
    void handle(VersionStack<GraphVisualization<PropertySupportingGraph>> stack);
}