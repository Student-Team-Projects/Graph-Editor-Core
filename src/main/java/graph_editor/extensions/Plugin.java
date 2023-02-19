package graph_editor.extensions;

import graph_editor.draw.IGraphDrawer;
import graph_editor.draw.point_mapping.CanvasDrawer;
import graph_editor.draw.point_mapping.PointMapper;
import graph_editor.properties.PropertySupportingGraph;

import java.util.Collections;

public abstract class Plugin {
    public interface Drawer {
        IGraphDrawer<PropertySupportingGraph> getGraphDrawer(PointMapper mapper, CanvasDrawer drawer);
    }
    public Iterable<Drawer> getGraphDrawers() {
        return Collections.emptyList();
    }
    public Iterable<String> usedDrawablesNames() {
        return Collections.emptyList();
    }
    public interface Proxy {
        boolean registerStackCapture(Plugin plugin, String name, StackCapture onSelection);
        boolean unregisterStackCapture(Plugin plugin, String name);
        boolean registerDeclaredPropertiesReader(Plugin plugin, String name, OnPropertyReaderSelection onSelection);
        boolean unregisterDeclaredPropertiesReader(Plugin plugin, String name);
        void releasePluginResources(Plugin plugin);
    }
    public abstract void activate(Proxy proxy);
    public abstract void deactivate(Proxy proxy);
    public abstract boolean supportsDirectedGraphs();
    public abstract boolean supportsUndirectedGraphs();
}
