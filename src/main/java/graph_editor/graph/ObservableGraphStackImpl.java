package graph_editor.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class ObservableGraphStackImpl implements GraphStack.ObservableGraphStack {
    private final Collection<Observer> observers;
    private final GraphStack stack;

    public ObservableGraphStackImpl(GraphStack stack) {
        this.observers = Collections.synchronizedSet(new HashSet<>());
        this.stack = stack;
    }

    @Override
    public void addObserver(Observer observer) { observers.add(observer); }
    @Override
    public void removeObserver(Observer observer) { observers.remove(observer); }

    @Override
    public void undo() {
        stack.undo();
        notifyObservers();
    }

    @Override
    public void redo() {
        stack.redo();
        notifyObservers();
    }

    @Override
    public void push(Graph graph) {
        stack.push(graph);
        notifyObservers();
    }

    @Override
    public boolean isUndoPossible() {
        return stack.isUndoPossible();
    }

    @Override
    public boolean isRedoPossible() {
        return stack.isRedoPossible();
    }

    @Override
    public Graph getCurrentGraph() { return stack.getCurrentGraph(); }

    @Override
    public Iterable<Graph> getGraphStack() { return stack.getGraphStack(); }

    private void notifyObservers() {
        observers.forEach(o -> o.notifyChange(getCurrentGraph()));
    }
}
