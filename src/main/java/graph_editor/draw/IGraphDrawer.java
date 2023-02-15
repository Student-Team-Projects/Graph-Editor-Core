package graph_editor.draw;

import graph_editor.graph.Graph;
import graph_editor.visual.GraphVisualization;

//TODO choose between this and GraphDrawer
public interface IGraphDrawer<T extends Graph> {
    void drawGraph(GraphVisualization<T> visual);
}
