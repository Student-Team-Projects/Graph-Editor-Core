package graph_editor.graph_generators;

import graph_editor.graph.DirectedGraph;
import graph_editor.visual.BuilderVisualizer;
import graph_editor.visual.GraphVisualization;

import java.util.ArrayList;
import java.util.List;

public class GraphGeneratorDirectedCycle implements GraphGenerator<DirectedGraph> {
    @Override
    public List<Parameter> getParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("Vertices", 3, 256));
        return parameters;
    }

    @Override
    public GraphVisualization<DirectedGraph> generate(List<Integer> parameters) {
        int cycleSize = parameters.get(0);
        DirectedGraph.DirectedGraphBuilder builder = new DirectedGraph.DirectedGraphBuilder(cycleSize);

        for (int i = 0; i < cycleSize; i++) {
            builder.addEdge(i, (i + 1)%cycleSize);
        }
        DirectedGraph graph = builder.build();

        BuilderVisualizer visualizer = new BuilderVisualizer();
        for (int i = 0; i < cycleSize; i++) {
            visualizer.addCoordinates(graph.getVertices().get(i),
                    GeometryHelper.getPointOnCycle(i, cycleSize));
        }

        return visualizer.generateVisual(graph);
    }
}
