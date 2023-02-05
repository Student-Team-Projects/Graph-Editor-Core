package graph_editor.graph_generators;

import graph_editor.geometry.Point;
import graph_editor.graph.Graph;
import graph_editor.graph.GraphBuilder;
import graph_editor.graph.SimpleGraphBuilder;
import graph_editor.visual.BuilderVisualizer;
import graph_editor.visual.GraphVisualization;

import java.util.ArrayList;
import java.util.List;

public class GraphGeneratorBipartiteClique implements GraphGenerator {
    @Override
    public List<Parameter> getParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("Left vertices", 1, 32));
        parameters.add(new Parameter("Right vertices", 1, 32));
        return parameters;
    }

    @Override
    public GraphVisualization<Graph> generate(List<Integer> parameters) {
        int left = parameters.get(0);
        int right = parameters.get(1);
        int verticesNumber = left + right;

        GraphBuilder builder = new SimpleGraphBuilder(verticesNumber);

        for (int i = 0; i < left; i++) {
            for (int j = left; j < verticesNumber; j++) {
                builder.addEdge(i, j);
            }
        }
        Graph graph = builder.build();

        BuilderVisualizer visualizer = new BuilderVisualizer();
        for (int i = 0; i < verticesNumber; i++) {
            visualizer.addCoordinates(graph.getVertices().get(i), getPointBipartite(i, left, right));
        }

        return visualizer.generateVisual(graph);
    }

    private Point getPointBipartite(int index, int left, int right) {
        int spaces = Math.max(right, left) - 1;
        double dist = spaces == 0 ? 1.0 : 1.0/spaces;

        Point result;
        if (index >= left) {
            double rightStart = right>left ? 1 : 1-(left-right)/2.0*dist;
            result = new Point(1, rightStart - dist*(index-left));
        } else {
            double leftStart = left>right ? 1 : 1-(right-left)/2.0*dist;
            result = new Point (0, leftStart - dist*index);
        }
        return result;
    }
}
