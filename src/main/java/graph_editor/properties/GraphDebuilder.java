package graph_editor.properties;

import java.util.Collection;
import java.util.Map;

import graph_editor.geometry.Point;
import graph_editor.graph.GenericGraphBuilder;
import graph_editor.graph.Graph;
import graph_editor.graph.Vertex;
import graph_editor.visual.GraphVisualizationBuilder;

public abstract class GraphDebuilder {
    public static PropertyGraphBuilder deBuild(
            PropertySupportingGraph graph,
            GenericGraphBuilder<? extends Graph> builder,
            GraphVisualizationBuilder visualizer,
            Collection<Map.Entry<Vertex, Point>> coordinates
    ) {
        graph.getEdges().forEach(edge -> builder.addEdge(edge.getSource().getIndex(), edge.getTarget().getIndex()));

        PropertyGraphBuilder propertyGraphBuilder = new PropertyGraphBuilder(builder.build());
        graph.getExtendedElements().forEach(propertyGraphBuilder::addExtendedElement);
        graph.getPropertiesNames().forEach(propertyName -> {
            propertyGraphBuilder.registerProperty(propertyName);
            graph.getElementsWithProperty(propertyName)
                    .forEach(graphElement ->
                            propertyGraphBuilder.addElementProperty(
                                    graphElement,
                                    propertyName,
                                    graph.getPropertyValue(propertyName, graphElement)
                            )
                    );
        });
        coordinates.forEach(e -> visualizer.addCoordinates(e.getKey(), e.getValue()));
        return propertyGraphBuilder;
    }
}
