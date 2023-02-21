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
            Map<Vertex, Point> coordinates
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
        PropertyGraphBuilder propertyGraphBuilder = new PropertyGraphBuilder(builder);
        graph.getExtendedElements().forEach(propertyGraphBuilder::addExtendedElement);
        copyProperties(graph, propertyGraphBuilder, vCorrespondence, eCorrespondence);
        coordinates.forEach((vertex, point) -> visualizer.addCoordinates(vCorrespondence.get(vertex), point));
        return propertyGraphBuilder;
    }

    public static void copyProperties(
            PropertySupportingGraph source,
            PropertyGraphBuilder target,
            Map<Vertex, Vertex> vCorrespondence,
            Map<Edge, Edge> eCorrespondence
    ) {
        source.getPropertiesNames().forEach(propertyName -> {
            target.registerProperty(propertyName);
            source.getVerticesWithProperty(propertyName).forEach(propertyVertex -> {
                Vertex corresponding = vCorrespondence.get(propertyVertex);
                if (corresponding != null) {
                    target.addElementProperty(
                            corresponding,
                            propertyName,
                            source.getPropertyValue(propertyName, propertyVertex)
                    );
                }
            });
            source.getEdgesWithProperty(propertyName).forEach(propertyEdge -> {
                Edge corresponding = eCorrespondence.get(propertyEdge);
                if (corresponding != null) {
                    target.addElementProperty(
                            corresponding,
                            propertyName,
                            source.getPropertyValue(propertyName, propertyEdge)
                    );
                }
            });
        });
    }
}
