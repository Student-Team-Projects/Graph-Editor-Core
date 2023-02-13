package graph_editor.graph_generators;

import java.util.List;

import graph_editor.graph.Graph;
import graph_editor.visual.GraphVisualization;

public interface GraphGenerator {
    List<Parameter> getParameters();
    GraphVisualization<Graph> generate(List<Integer> parameters);
}
