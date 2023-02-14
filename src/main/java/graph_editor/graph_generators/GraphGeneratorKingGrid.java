package graph_editor.graph_generators;

import graph_editor.geometry.GeometryUtils;
import graph_editor.graph.UndirectedGraph;
import graph_editor.visual.BuilderVisualizer;
import graph_editor.visual.GraphVisualization;

import java.util.ArrayList;
import java.util.List;

public class GraphGeneratorKingGrid implements GraphGenerator<UndirectedGraph> {
    @Override
    public List<Parameter> getParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("Horizontal", 1, 32));
        parameters.add(new Parameter("Vertical", 1, 32));
        return parameters;
    }

    @Override
    public GraphVisualization<UndirectedGraph> generate(List<Integer> parameters) {
        int hor = parameters.get(0);
        int ver = parameters.get(1);
        UndirectedGraph.Builder builder = new UndirectedGraph.Builder(hor*ver);

        //horizontal edges
        for (int i = 0; i < ver; i++) {
            for (int j = 0; j < hor-1; j++) {
                builder.addEdge(i*hor + j, i*hor + (j+1));
            }
        }
        //vertical edges
        for (int i = 0; i < ver-1; i++) {
            for (int j = 0; j < hor; j++) {
                builder.addEdge(i*hor + j, (i+1)*hor + j);
            }
        }
        //slanted like \
        for (int i = 0; i < ver-1; i++) {
            for (int j = 0; j < hor-1; j++) {
                builder.addEdge(i*hor + j, (i+1)*hor + (j+1));
            }
        }
        //slanted like /
        for (int i = 0; i < ver-1; i++) {
            for (int j = 1; j < hor; j++) {
                builder.addEdge(i*hor + j, (i+1)*hor + (j-1));
            }
        }
        UndirectedGraph graph = builder.build();

        BuilderVisualizer visualizer = new BuilderVisualizer();
        for (int i = 0; i < ver; i++) {
            for (int j = 0; j < hor; j++) {
                visualizer.addCoordinates(graph.getVertices().get(i*hor + j),
                        GeometryUtils.getGridPoint(j, i, hor, ver));
            }
        }
        return visualizer.generateVisual(graph);
    }
}
