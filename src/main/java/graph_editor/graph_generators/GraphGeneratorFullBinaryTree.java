package graph_editor.graph_generators;

import graph_editor.geometry.GeometryUtils;
import graph_editor.graph.UndirectedGraph;
import graph_editor.visual.BuilderVisualizer;
import graph_editor.visual.GraphVisualization;

import java.util.ArrayList;
import java.util.List;

public class GraphGeneratorFullBinaryTree implements GraphGenerator<UndirectedGraph> {
    @Override
    public List<Parameter> getParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("Layers", 2, 8));
        return parameters;
    }

    @Override
    public GraphVisualization<UndirectedGraph> generate(List<Integer> parameters) {
        int layers = parameters.get(0);
        int vertexCount = (1 << layers) - 1;
        UndirectedGraph.Builder builder = new UndirectedGraph.Builder(vertexCount);

        for (int i = 0; i < vertexCount/2; i++) {
            builder.addEdge(i, (i+1)*2 - 1);
            builder.addEdge(i, (i+1)*2);
        }
        UndirectedGraph graph = builder.build();

        BuilderVisualizer visualizer = new BuilderVisualizer();
        for (int i = 0; i < vertexCount; i++) {
            visualizer.addCoordinates(graph.getVertices().get(i),
                    GeometryUtils.getPointBinaryTree(i, layers));
        }

        return visualizer.generateVisual(graph);
    }
}
