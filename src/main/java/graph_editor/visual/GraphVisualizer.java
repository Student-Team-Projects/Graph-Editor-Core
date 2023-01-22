package graph_editor.visual;

import graph_editor.graph.Graph;

public interface GraphVisualizer {
    <T extends Graph> GraphVisualization<T> generateVisual(T graph);
}