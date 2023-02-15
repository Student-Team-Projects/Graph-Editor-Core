package graph_editor.visual;

import graph_editor.geometry.Point;
import graph_editor.graph.Edge;
import graph_editor.graph.Graph;
import graph_editor.graph.Vertex;

public class PlanarGraphVisualizer {
    public <T extends Graph> GraphVisualization<T> generateVisual(T graph, GraphVisualization graphVisualization, String type) {
        double[] tabX = new double[graph.getVertices().size()];
        double[] tabY = new double[graph.getVertices().size()];
        int[] tabEdgeSource = new int[graph.getEdges().size()];
        int[] tabEdgeTarget = new int[graph.getEdges().size()];
        System.out.println(graph.getVertices().size());
        for(Vertex vertex : graph.getVertices()) {
            Point point = graphVisualization.getVertexPoint(vertex);
            System.out.println(vertex.getIndex());
            System.out.println(point);
            tabX[vertex.getIndex()] = point.getX();
            tabY[vertex.getIndex()] = point.getY();
        }
        int j = 0;
        for(Edge edge : graph.getEdges()) {
            tabEdgeSource[j] = edge.getSource().getIndex();
            tabEdgeTarget[j] = edge.getTarget().getIndex();
            j++;
        }
        caseowanie się po type
        double[] new_pos = funkcja_z_cpp(graph.getVertices().size(), graph.getEdges().size(), tabX, tabY, tabEdgeSource, tabEdgeTarget);

        GraphVisualizationImpl<T> visualization = new GraphVisualizationImpl<>(graph);
        for (Vertex vertex : graph.getVertices()) {
            visualization.setCoord(
                    vertex, new Point(new_pos[vertex.getIndex()], new_pos[vertex.getIndex() + graph.getVertices().size()]));
        }
        return visualization;
    }
}
