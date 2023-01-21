package graph_editor.graph_generators;

import java.util.List;

import graph_editor.visual.GraphVisualization;

public interface GraphGenerator {
    List<Parameter> getParameters();
    GraphVisualization generate(List<Integer> parameters);
}
