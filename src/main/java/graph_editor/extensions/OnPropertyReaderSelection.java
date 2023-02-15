package graph_editor.extensions;

import graph_editor.properties.PropertyWriter;

import java.util.List;

public interface OnPropertyReaderSelection {
    interface SettingChoice {
        String getName();
        void choose();
    }
    List<SettingChoice> handle(List<PropertyWriter> writers);
}
