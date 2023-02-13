package graph_editor.visual;

import graph_editor.geometry.Point;
import graph_editor.graph.Graph;
import graph_editor.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class BuilderVisualizer implements GraphVisualizer, GraphVisualizationBuilder {
    private static class Mapping {
        private final Vertex v;
        private final Point p;
        private Mapping(Vertex v, Point p) {
            this.v = v;
            this.p = p;
        }
    }
    private final List<Mapping> verticesCoordinates = new ArrayList<>();
    @Override
    public void addCoordinates(Vertex v, Point p) {
        verticesCoordinates.add(new Mapping(v, p));
    }

    @Override
    public <T extends Graph> GraphVisualization<T> generateVisual(T graph) {
        GraphVisualizationImpl<T> visualization = new GraphVisualizationImpl<T>(graph);
        verticesCoordinates.forEach(m -> visualization.setCoord(m.v, m.p));
        verticesCoordinates.clear();
        return visualization;
    }
}
