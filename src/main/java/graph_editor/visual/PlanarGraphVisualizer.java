package graph_editor.visual;

import graph_editor.geometry.Point;
import graph_editor.graph.*;
import com.example.graph_editor.jni.Tools;

@SuppressWarnings("unused")
public class PlanarGraphVisualizer {
    public enum PlanarAlgorithm {
        fixArrangingWithForces,
        fixPlanarArranging,
        findPlanarArrange,
    }
    @SuppressWarnings("unused")
    public <T extends Graph> GraphVisualization<T> generateVisual(T graph, GraphVisualization<T> graphVisualization, PlanarAlgorithm type) {
        double[] tabX = new double[graph.getVertices().size()];
        double[] tabY = new double[graph.getVertices().size()];
        int[] tabEdgeSource = new int[graph.getEdges().size()];
        int[] tabEdgeTarget = new int[graph.getEdges().size()];

        for(Vertex vertex : graph.getVertices()) {
            Point point = graphVisualization.getVertexPoint(vertex);
            tabX[vertex.getIndex()] = point.getX();
            tabY[vertex.getIndex()] = point.getY();
        }
        int j = 0;
        for(Edge edge : graph.getEdges()) {
            tabEdgeSource[j] = edge.getSource().getIndex();
            tabEdgeTarget[j] = edge.getTarget().getIndex();
            j++;
        }
        double[] new_pos;
        Tools t = new Tools();
        switch (type) {
            case fixArrangingWithForces: {
                new_pos = t.arrange(graph.getVertices().size(), graph.getEdges().size(), tabX, tabY, tabEdgeSource, tabEdgeTarget);
                break;
            }
            case fixPlanarArranging: {
                new_pos = t.arrangePlanar(graph.getVertices().size(), graph.getEdges().size(), tabX, tabY, tabEdgeSource, tabEdgeTarget);
                break;
            }
            case findPlanarArrange: {
                new_pos = t.makePlanar(graph.getVertices().size(), graph.getEdges().size(), tabX, tabY, tabEdgeSource, tabEdgeTarget);
                if (new_pos[0] == -6969696969696969.) {
                    return graphVisualization;
                }
                // TODO: returns -6969696969696969 if not planar. idk if we can import constant or not from jni. imo great constant
                break;
            }
            default: {
                throw new RuntimeException("Algorithm unknown, fix enum");
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
