package graph_editor.extensions;

import java.util.List;

public interface OnPropertyReaderSelection {
    interface SettingChoice {
        String getName();
        void choose();
    }
    List<SettingChoice> handle(List<String> availableProperties);
}
