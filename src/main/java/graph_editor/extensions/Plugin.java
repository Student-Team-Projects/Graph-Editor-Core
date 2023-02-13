package graph_editor.extensions;

import graph_editor.draw.GraphDrawer;

public interface Plugin {
    interface Proxy {
        boolean registerOption(Plugin plugin, String name, OnOptionSelection onOptionSelection);
        boolean deregisterOption(Plugin plugin, String name);
        boolean acquireDrawingBehaviour(Plugin plugin, GraphDrawer drawer);
        void releaseDrawingBehaviour(Plugin plugin);
        void releasePluginResources(Plugin plugin);
    }

    void activate(Proxy proxy);
    void deactivate(Proxy proxy);
}
