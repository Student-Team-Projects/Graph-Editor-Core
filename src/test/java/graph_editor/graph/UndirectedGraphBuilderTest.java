package graph_editor.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class UndirectedGraphBuilderTest {
    @Test
    void shouldIncrementVertexIndex() {
        UndirectedGraph.UndirectedGraphBuilder builder = new UndirectedGraph.UndirectedGraphBuilder();
        Vertex v0 = builder.addVertex();
        Vertex v1 = builder.addVertex();
        Vertex v2 = builder.addVertex();

        assertEquals(0, v0.getIndex());
        assertEquals(1, v1.getIndex());
        assertEquals(2, v2.getIndex());
    }

    @Test
    void shouldAddUndirectedEdge() {
        UndirectedGraph.UndirectedGraphBuilder builder = new UndirectedGraph.UndirectedGraphBuilder();
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
        assertIterableEquals(emptyList(), v1.getAdjacent());
    }

    @Test
    void shouldNotDuplicateEdge() {
        UndirectedGraph.UndirectedGraphBuilder builder = new UndirectedGraph.UndirectedGraphBuilder();
        builder.addVertex();
        builder.addVertex();
        builder.addEdge(0, 1);
        builder.addEdge(1, 0);
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