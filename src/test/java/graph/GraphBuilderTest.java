package graph_editor.graph;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class GraphBuilderTest {
    @Test
    void buildSimpleGraph() {
        GraphBuilderImpl builder = new GraphBuilderImpl(3);
        builder.addBidirectionalEdge(0, 1);
        builder.addBidirectionalEdge(1, 2);
        builder.addBidirectionalEdge(2, 0);

        Graph graph = builder.build();
        Assertions.assertEquals(graph.getVertices().size(), 3);
    }

    @Test
    void graphIsImmutable() {
        GraphBuilderImpl builder = new GraphBuilderImpl(3);
        builder.addBidirectionalEdge(0, 1);
        builder.addBidirectionalEdge(1, 2);
        builder.addBidirectionalEdge(2, 0);

        Graph graph = builder.build();
        builder.addVertex();
        Assertions.assertEquals(graph.getVertices().size(), 3);
    }


    @Test
    void undoTest() {
        GraphBuilderImpl builder = new GraphBuilderImpl(3);
        builder.addBidirectionalEdge(0, 1);
        builder.addBidirectionalEdge(1, 2);
        builder.addBidirectionalEdge(2, 0);

        Graph graph = builder.build();
        builder.addVertex();
        builder.addBidirectionalEdge(3, 0);
        builder.build();

        builder.goToPrevBuild();
        Assertions.assertTrue(graph.equals(builder.build()));
    }

    @Test
    void redoTest() {
        GraphBuilderImpl builder = new GraphBuilderImpl(3);
        builder.addBidirectionalEdge(0, 1);
        builder.addBidirectionalEdge(1, 2);
        builder.addBidirectionalEdge(2, 0);

        builder.build();
        builder.addVertex();
        builder.addBidirectionalEdge(3, 0);
        Graph graph = builder.build();

        builder.goToPrevBuild();
        builder.undoGoingToPrevBuild();
        Assertions.assertTrue(graph.equals(builder.build()));
    }
}