package graph_editor.draw;

import graph_editor.visual.*;
import graph_editor.graph.*;
import graph_editor.geometry.*;

import java.util.*;

public abstract class AbstractGraphDrawer<T extends Graph> implements GraphDrawer<T> {
    @Override
    public void drawGraph(GraphVisualization<T> visual) {
        this.visualization = visual;
        this.graph = visual.getGraph();
        visited_nodes = new HashSet<Vertex>();

        dfsDraw(graph.getVertices().get(0));
    }

    @Override
    public List<Geometry> getPrimitiveGrometries(GraphVisualization<T> visual) {
        List<Geometry> geometries = new ArrayList<>();
        for (Vertex vertex : visual.getGraph().getVertices()) {
            geometries.add(new Circle(visual.getVertexPoint(vertex), CIRCLE_RADIUS));
        }
        for (Edge edge : visual.getGraph().getEdges()) {
            geometries.add(new Line(
                visual.getVertexPoint(edge.getSource()), visual.getVertexPoint(edge.getTarget())));
        }
        return geometries;
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

    protected final double CIRCLE_RADIUS = 20;

    protected abstract void moveCursorTo(Point p);
    protected abstract void drawLineTo(Point p);
    protected abstract void drawCircle(Point p);

    private Graph graph;
    private GraphVisualization<T> visualization;
    private Set<Vertex> visited_nodes;
}