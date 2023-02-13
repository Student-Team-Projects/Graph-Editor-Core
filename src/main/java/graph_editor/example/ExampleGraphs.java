package graph_editor.example;

import graph_editor.graph.*;

public class ExampleGraphs {
    public static Graph triangle() {
        GraphBuilderImpl builder = new GraphBuilderImpl(3);
        builder.addBidirectionalEdge(0, 1);
        builder.addBidirectionalEdge(1, 2);
        builder.addBidirectionalEdge(2, 0);
        return builder.build();
    }

    public static Graph tree() {
        GraphBuilderImpl builder = new GraphBuilderImpl(6);
        builder.addBidirectionalEdge(0, 3);
        builder.addBidirectionalEdge(1, 3);
        builder.addBidirectionalEdge(2, 3);
        builder.addBidirectionalEdge(3, 4);
        builder.addBidirectionalEdge(4, 5);
        return builder.build();
    }

    private ExampleGraphs() {}
}