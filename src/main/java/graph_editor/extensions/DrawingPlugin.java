package graph_editor.extensions;

import graph_editor.draw.GraphDrawer;
import graph_editor.graph.Graph;

public interface DrawingPlugin extends Plugin {
    GraphDrawer getGraphDrawer();
}
