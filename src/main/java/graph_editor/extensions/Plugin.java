package graph_editor.extensions;

import graph_editor.draw.point_mapping.CanvasDrawer;
import graph_editor.properties.PropertyUser;

public interface Plugin<T extends Plugin.Proxy> extends PropertyUser {
    interface Proxy {
        boolean registerOption(Plugin<?> plugin, String name, OnOptionSelection onOptionSelection);
        boolean deregisterOption(Plugin<?> plugin, String name);

        void releasePluginResources(Plugin<?> plugin);
    }

    void activate(T proxy);
    void deactivate(T proxy);
}
