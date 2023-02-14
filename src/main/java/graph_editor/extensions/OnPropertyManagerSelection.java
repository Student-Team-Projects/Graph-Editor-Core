package graph_editor.extensions;

import graph_editor.properties.PropertyUser;

import java.util.List;

public interface OnPropertyManagerSelection {
    interface SettingChoice {
        String getName();
        void choose();
    }
    List<SettingChoice> handle(List<PropertyUser> users);
}
