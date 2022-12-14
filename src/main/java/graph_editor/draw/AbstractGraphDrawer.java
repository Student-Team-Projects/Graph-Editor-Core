package graph_editor.draw;

import graph_editor.visual.*;
import graph_editor.graph.*;
import graph_editor.geometry.Point;

import java.util.*;

public abstract class AbstractGraphDrawer implements GraphDrawer {
    @Override
    public void drawGraph(GraphVisualization visual) {
        this.visualization = visual;
        this.graph = visual.getGraph();
        visited_nodes = new HashSet<Vertex>();

        dfsDraw(graph.getVertices().get(0));
    }

    private void dfsDraw(Vertex v) {
        if (visited_nodes.contains(v)) {
            return;
        }
        visited_nodes.add(v);

        Point cur_point = visualization.getVertexPoint(v);
        moveCursorTo(cur_point);
        drawCircle(cur_point);

        for (Vertex to : v.getAdjacent()) {
            Point next_point = visualization.getVertexPoint(to);
            drawLineTo(next_point);
            dfsDraw(to);
            drawLineTo(next_point);
        }
    }

    abstract void moveCursorTo(Point p);
    abstract void drawLineTo(Point p);
    abstract void drawCircle(Point p);

    private Graph graph;
    private GraphVisualization visualization;
    private Set<Vertex> visited_nodes;
}