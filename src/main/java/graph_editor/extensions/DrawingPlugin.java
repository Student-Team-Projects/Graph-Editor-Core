package graph_editor.extensions;

import graph_editor.draw.IGraphDrawer;
import graph_editor.draw.point_mapping.CanvasDrawer;
import graph_editor.draw.point_mapping.PointMapper;
import graph_editor.properties.PropertySupportingGraph;

public interface DrawingPlugin extends Plugin<OnPropertyReaderSelection> {
    IGraphDrawer<PropertySupportingGraph> getGraphDrawer(PointMapper mapper, CanvasDrawer drawer);
}