package graph_editor.graph_generators;

import java.util.List;

import graph_editor.graph.Graph;

public interface GraphGenerator {
    List<Parameter> getParameters();
    Graph generate(List<Integer> parameters); //also assigns Points between (0, 0) and (1, 1)
}
