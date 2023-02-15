package graph_editor.extensions;

public class Extension<T> {
    private final String name;
    private final Plugin<T> plugin;
    private final Plugin.Proxy<T> proxy;
    private boolean enabled = false;

    public Extension(String name, Plugin<T> plugin, Plugin.Proxy<T> proxy) {
        this.name = name;
        this.plugin = plugin;
        this.proxy = proxy;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean value) {
        if (enabled == value) return;
        enabled = value;
        if (enabled) {
            plugin.activate(proxy);
        } else {
            plugin.deactivate(proxy);
        }
    }
    public String getName() { return name; }
}
