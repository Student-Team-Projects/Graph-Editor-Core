package graph_editor.properties;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import graph_editor.geometry.Point;
import graph_editor.graph.*;
import graph_editor.visual.GraphVisualizationBuilder;

public abstract class GraphDebuilder {
    public static PropertyGraphBuilder deBuild(
            PropertySupportingGraph graph,
            GenericGraphBuilder<? extends Graph> builder,
            GraphVisualizationBuilder visualizer,
            Collection<Map.Entry<Vertex, Point>> coordinates
    ) {
        Map<Vertex, Vertex> vCorrespondence = new HashMap<>();
        Map<Edge, Edge> eCorrespondence = new HashMap<>();
        graph.getVertices().forEach(v -> {
            vCorrespondence.put(v, builder.addVertex());
        });
        graph.getEdges().forEach(edge -> {
            eCorrespondence.put(
                    edge,
                    builder.addEdge(edge.getSource().getIndex(), edge.getTarget().getIndex()).get()
            );
        });

        PropertyGraphBuilder propertyGraphBuilder = new PropertyGraphBuilder(builder.build());
        graph.getExtendedElements().forEach(propertyGraphBuilder::addExtendedElement);
        graph.getPropertiesNames().forEach(propertyName -> {
            propertyGraphBuilder.registerProperty(propertyName);
            graph.getVerticesWithProperty(propertyName).forEach(propertyVertex -> {
                Vertex corresponding = vCorrespondence.get(propertyVertex);
                propertyGraphBuilder.addElementProperty(
                    corresponding,
                    propertyName,
                    graph.getPropertyValue(propertyName, propertyVertex)
                );
            });
            graph.getEdgesWithProperty(propertyName).forEach(propertyEdge -> {
                Edge corresponding = eCorrespondence.get(propertyEdge);
                propertyGraphBuilder.addElementProperty(
                        corresponding,
                        propertyName,
                        graph.getPropertyValue(propertyName, propertyEdge)
                );
            });
        });
        coordinates.forEach(e -> visualizer.addCoordinates(e.getKey(), e.getValue()));
        return propertyGraphBuilder;
    }
}
