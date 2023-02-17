package graph_editor.extensions;

public class Extension<T> {
    private final String name;
    private final Plugin plugin;
    private final Plugin.Proxy proxy;
    private boolean enabled = false;

    public Extension(String name, Plugin plugin, Plugin.Proxy proxy) {
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
