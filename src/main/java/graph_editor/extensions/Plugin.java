package graph_editor.extensions;

import graph_editor.draw.GraphDrawer;

public interface Plugin {
    interface DrawingPlugin extends Plugin { }
    interface Proxy {
        boolean registerOption(Plugin plugin, String name, OnOptionSelection onOptionSelection);
        boolean deregisterOption(Plugin plugin, String name);
        boolean acquireDrawingBehaviour(DrawingPlugin plugin, GraphDrawer drawer);
        void releaseDrawingBehaviour(DrawingPlugin plugin);
        void releasePluginResources(Plugin plugin);
    }

    void activate(Proxy proxy);
    void deactivate(Proxy proxy);
}
