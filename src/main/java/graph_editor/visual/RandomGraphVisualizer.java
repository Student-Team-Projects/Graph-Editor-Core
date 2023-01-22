package graph_editor.visual;

import java.util.*;

import graph_editor.graph.*;
import graph_editor.geometry.Point;

// Assigns random coord in rectangle.
public class RandomGraphVisualizer implements GraphVisualizer {
    
    public static RandomGraphVisualizer byWidthAndHeight(int width, int height) {
        return new RandomGraphVisualizer(width, height);
    }

    public static GraphVisualization<Graph> getVisualization(Graph graph, int width, int height) {
        RandomGraphVisualizer visualizer = new RandomGraphVisualizer(width, height);
        return visualizer.generateVisual(graph);
    }

    @Override
    public <T extends Graph> GraphVisualization<T> generateVisual(T graph) {
        GraphVisualizationImpl<T> visualization = new GraphVisualizationImpl<>(graph);
        for (Vertex vertex : graph.getVertices()) {
            visualization.setCoord(
                vertex, new Point(rng.nextInt(width), rng.nextInt(height)));
        }
        return visualization;
    }

    private RandomGraphVisualizer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private static Random rng = new Random();
    private int width;
    private int height;
} 