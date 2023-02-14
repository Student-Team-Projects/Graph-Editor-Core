package graph_editor.extensions;

import graph_editor.draw.GraphDrawer;
import graph_editor.properties.PropertyUser;

public interface Plugin extends PropertyUser {
    interface Proxy {
        boolean registerOption(Plugin plugin, String name, OnOptionSelection onOptionSelection);
        boolean deregisterOption(Plugin plugin, String name);

        boolean registerPropertyOption(Plugin plugin, String name, OnPropertyManagerSelection onPropertyManagerSelection);
        boolean deregisterPropertyOption(Plugin plugin, String name);

        void releasePluginResources(Plugin plugin);
    }

    void activate(Proxy proxy);
    void deactivate(Proxy proxy);
}
