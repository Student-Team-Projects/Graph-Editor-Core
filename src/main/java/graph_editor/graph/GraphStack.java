package graph_editor.graph;

public interface GraphStack {
    interface ObservableGraphStack extends GraphStack {
        interface Observer {
            void notifyChange(Graph top);
        }
        void addObserver(Observer observer);
        void removeObserver(Observer observer);
    }

    void undo();
    void redo();
    void push(Graph graph);
    boolean isUndoPossible();
    boolean isRedoPossible();
    Graph getCurrentGraph();
    Iterable<Graph> getGraphStack();
}
