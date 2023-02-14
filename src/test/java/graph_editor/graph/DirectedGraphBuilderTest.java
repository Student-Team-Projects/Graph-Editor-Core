package graph_editor.graph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static java.util.Collections.singletonList;

class DirectedGraphBuilderTest {
    @Test
    void shouldIncrementVertexIndex() {
        DirectedGraph.DirectedGraphBuilder builder = new DirectedGraph.DirectedGraphBuilder();
        Vertex v0 = builder.addVertex();
        Vertex v1 = builder.addVertex();
        Vertex v2 = builder.addVertex();

        assertEquals(0, v0.getIndex());
        assertEquals(1, v1.getIndex());
        assertEquals(2, v2.getIndex());
    }

    @Test
    void shouldAddDirectedEdge() {
        DirectedGraph.DirectedGraphBuilder builder = new DirectedGraph.DirectedGraphBuilder();
        builder.addVertex();
        builder.addVertex();
        builder.addEdge(0, 1);

        Graph graph = builder.build();
        List<Vertex> vertices = graph.getVertices();
        assertEquals(2, vertices.size());
        assertEquals(1, graph.getEdges().size());
        Vertex v0 = vertices.get(0);
        Vertex v1 = vertices.get(1);
        assertIterableEquals(singletonList(v1), v0.getAdjacent());
        assertTrue(v1.getAdjacent().isEmpty());
    }

    @Test
    void shouldNotDuplicateEdge() {
        DirectedGraph.DirectedGraphBuilder builder = new DirectedGraph.DirectedGraphBuilder();
        builder.addVertex();
        builder.addVertex();
        builder.addEdge(0, 1);
        builder.addEdge(0, 1);

        Graph graph = builder.build();
        List<Vertex> vertices = graph.getVertices();
        assertEquals(2, vertices.size());
        assertEquals(1, graph.getEdges().size());
        Vertex v0 = vertices.get(0);
        Vertex v1 = vertices.get(1);
        assertIterableEquals(singletonList(v1), v0.getAdjacent());
        assertIterableEquals(emptyList(), v1.getAdjacent());
    }
}