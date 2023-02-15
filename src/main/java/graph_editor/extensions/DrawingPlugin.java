package graph_editor.extensions;

import graph_editor.draw.IGraphDrawer;
import graph_editor.properties.PropertySupportingGraph;

public interface DrawingPlugin extends Plugin<OnPropertyManagerSelection> {
    IGraphDrawer<PropertySupportingGraph> getGraphDrawer();
}