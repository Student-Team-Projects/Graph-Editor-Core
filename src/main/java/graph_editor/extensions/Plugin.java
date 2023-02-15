package graph_editor.extensions;

public interface Plugin<T> {
    interface Proxy<T> {
        boolean registerOnSelection(Plugin<T> plugin, String name, T onSelection);
        boolean deregisterOnSelection(Plugin<T> plugin, String name);
        void releasePluginResources(Plugin<T> plugin);
    }
    void activate(Proxy<T> proxy);
    void deactivate(Proxy<T> proxy);
}

