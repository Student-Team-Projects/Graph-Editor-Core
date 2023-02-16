package graph_editor.extensions;

import graph_editor.draw.IGraphDrawer;
import graph_editor.draw.point_mapping.CanvasDrawer;
import graph_editor.draw.point_mapping.PointMapper;
import graph_editor.properties.PropertySupportingGraph;

public interface Plugin {
    interface DrawingPlugin extends Plugin {
        IGraphDrawer<PropertySupportingGraph> getGraphDrawer(PointMapper mapper, CanvasDrawer drawer);
    }
    interface DrawablePropertyUser extends Plugin {
        Iterable<String> usedDrawablesNames();
    }
    interface Proxy {
        boolean registerStackCapture(Plugin plugin, String name, StackCapture onSelection);
        boolean unregisterStackCapture(Plugin plugin, String name);
        boolean registerDeclaredPropertiesReader(Plugin plugin, String name, OnPropertyReaderSelection onSelection);
        boolean unregisterDeclaredPropertiesReader(Plugin plugin, String name);
        void releasePluginResources(Plugin plugin);
    }
    void activate(Proxy proxy);
    void deactivate(Proxy proxy);
    boolean supportsDirectedGraphs();
    boolean supportsUndirectedGraphs();
}
