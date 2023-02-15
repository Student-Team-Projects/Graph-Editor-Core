package graph_editor.extensions;

import java.util.List;

public interface ExtensionsRepository {
    List<Extension<OnPropertyManagerSelection>> getDrawingExtensions();
    List<Extension<OnOptionSelection>> getEditingExtensions();
    boolean isPresent(String extensionName);
    boolean add(String extensionsName);
    boolean remove(String extensionsName);
}
