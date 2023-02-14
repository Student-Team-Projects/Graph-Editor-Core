package graph_editor.extensions;

public class PluginConfigDto {
    private String jarName;
    private String pluginClassName;
    private int version;

    public String getJarName() {
        return jarName;
    }
    public String getPluginClassName() {
        return pluginClassName;
    }
    public int getVersion() {
        return version;
    }
}