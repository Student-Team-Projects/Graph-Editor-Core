package graph_editor.extensions;

import graph_editor.draw.GraphDrawer;
import graph_editor.draw.point_mapping.CanvasDrawer;
import graph_editor.draw.point_mapping.PointMapper;
import graph_editor.properties.PropertySupportingGraph;

public interface DrawingPlugin extends Plugin<DrawingPlugin.DrawingProxy> {
    GraphDrawer<PropertySupportingGraph> getGraphDrawer(PointMapper mapper, CanvasDrawer canvasDrawer);

    interface DrawingProxy extends Plugin.Proxy {
        boolean registerPropertyOption(DrawingPlugin plugin, String name, OnPropertyManagerSelection onPropertyManagerSelection);
        boolean deregisterPropertyOption(DrawingPlugin plugin, String name);
        void saveDrawingBehaviour(DrawingPlugin plugin, DrawingBehaviour behaviour);
    }
}
