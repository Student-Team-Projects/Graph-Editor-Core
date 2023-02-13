package graph_editor.graph;

public interface VersionStack<T> {
    interface ObservableStack<U> extends VersionStack<U> {
        interface Observer<V> {
            void notifyChange(V top);
        }
        void addObserver(Observer<U> observer);
        void removeObserver(Observer<U> observer);
    }

    void undo();
    void redo();
    void push(T element);
    boolean isUndoPossible();
    boolean isRedoPossible();
    T getCurrent();
    Iterable<T> getStack();
}
