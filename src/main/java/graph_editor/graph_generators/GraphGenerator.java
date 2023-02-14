package graph_editor.graph_generators;

import java.util.List;

import graph_editor.graph.Graph;
import graph_editor.visual.GraphVisualization;

public interface GraphGenerator<G extends Graph> {
    List<Parameter> getParameters();
    GraphVisualization<G> generate(List<Integer> parameters);
}
