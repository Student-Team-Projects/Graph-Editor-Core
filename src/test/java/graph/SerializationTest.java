package graph_editor.graph;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import graph_editor.graph.*;
import java.io.*;

class SerializationTest {
    @Test
    void buildSimpleGraph() {
        GraphBuilder builder = new GraphBuilder(3);
        builder.addBidirectionalEdge(0, 1);
        builder.addBidirectionalEdge(1, 2);
        builder.addBidirectionalEdge(2, 0);

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            Graph graph = builder.build();
            ObjectOutputStream oos = new ObjectOutputStream(buffer);
            GraphBuilder.writeToStream(graph, oos);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            Graph deserialized_graph = GraphBuilder.readFromStream(ois);
            Assertions.assertEquals(deserialized_graph, graph);
        } catch(Exception e) {
            Assertions.fail();
        }
    }
}