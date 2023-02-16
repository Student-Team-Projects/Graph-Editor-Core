package graph_editor.visual;

import graph_editor.geometry.Point;
import graph_editor.graph.*;
import com.example.graph_editor.jni.Tools;

public class PlanarGraphVisualizer {
    public <T extends Graph> GraphVisualization<T> generateVisual(T graph, GraphVisualization<T> graphVisualization, String type) {
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
        System.out.println(type);
        double[] new_pos;
        Tools t = new Tools();
        switch (type) {
            case "arrange": {
                new_pos = t.arrange(graph.getVertices().size(), graph.getEdges().size(), tabX, tabY, tabEdgeSource, tabEdgeTarget);
                break;
            }
            case "arrangePlanar": {
                new_pos = t.arrangePlanar(graph.getVertices().size(), graph.getEdges().size(), tabX, tabY, tabEdgeSource, tabEdgeTarget);
                break;
            }
            case "planar": {
                new_pos = t.makePlanar(graph.getVertices().size(), graph.getEdges().size(), tabX, tabY, tabEdgeSource, tabEdgeTarget);
                break;
            }
            default: {
                throw new RuntimeException("Algorithm unknown");
            }
        }
        GraphVisualizationImpl<T> visualization = new GraphVisualizationImpl<>(graph);
        for (Vertex vertex : graph.getVertices()) {
            visualization.setCoord(
                    vertex, new Point(new_pos[vertex.getIndex()], new_pos[vertex.getIndex() + graph.getVertices().size()]));
        }
        return visualization;
    }
}
