package graph_editor.graph;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import graph_editor.graph.*;

class GraphBuilderTest {
    @Test
    void buildSimpleGraph() {
        GraphBuilder builder = new GraphBuilder(3);
        builder.addBidirectionalEdge(0, 1);
        builder.addBidirectionalEdge(1, 2);
        builder.addBidirectionalEdge(2, 0);

        Graph graph = builder.build();
        Assertions.assertEquals(graph.getVertices().size(), 3);
    }

    @Test
    void graphIsImmutable() {
        GraphBuilder builder = new GraphBuilder(3);
        builder.addBidirectionalEdge(0, 1);
        builder.addBidirectionalEdge(1, 2);
        builder.addBidirectionalEdge(2, 0);

        Graph graph = builder.build();
        builder.addVertex();
        Assertions.assertEquals(graph.getVertices().size(), 3);
    }


    @Test
    void undoTest() {
        GraphBuilder builder = new GraphBuilder(3);
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
        GraphBuilder builder = new GraphBuilder(3);
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