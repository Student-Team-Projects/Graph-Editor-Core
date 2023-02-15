package graph_editor.graph_generators;

import graph_editor.graph.UndirectedGraph;
import graph_editor.visual.BuilderVisualizer;
import graph_editor.visual.GraphVisualization;
import graph_editor.geometry.GeometryUtils;

import java.util.ArrayList;
import java.util.List;

public class GraphGeneratorClique implements GraphGenerator<UndirectedGraph> {

    @Override
    public List<Parameter> getParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("Vertices", 3, 64));
        return parameters;
    }

    @Override
    public GraphVisualization<UndirectedGraph> generate(List<Integer> parameters) {
        int cliqueSize = parameters.get(0);
        UndirectedGraph.Builder builder = new UndirectedGraph.Builder(cliqueSize);

        for (int i = 0; i < cliqueSize; i++) {
            for (int j = i + 1; j < cliqueSize; j++) {
                builder.addEdge(i, j);
            }
        }
        UndirectedGraph graph = builder.build();

        BuilderVisualizer visualizer = new BuilderVisualizer();
        for (int i = 0; i < cliqueSize; i++) {
            visualizer.addCoordinates(graph.getVertices().get(i),
                    GeometryUtils.getPointOnCircle(i, cliqueSize));
        }

        return visualizer.generateVisual(graph);
    }
}
