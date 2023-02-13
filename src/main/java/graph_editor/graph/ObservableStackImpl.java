package graph_editor.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class ObservableStackImpl<T> implements VersionStack.ObservableStack<T> {
    private final Collection<Observer<T>> observers;
    private final VersionStack<T> stack;

    public ObservableStackImpl(VersionStack<T> stack) {
        this.observers = Collections.synchronizedSet(new HashSet<>());
        this.stack = stack;
    }

    @Override
    public void addObserver(Observer<T> observer) { observers.add(observer); }
    @Override
    public void removeObserver(Observer<T> observer) { observers.remove(observer); }

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
    public void push(T element) {
        stack.push(element);
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
    public T getCurrent() { return stack.getCurrent(); }

    @Override
    public Iterable<T> getStack() { return stack.getStack(); }

    private void notifyObservers() {
        observers.forEach(o -> o.notifyChange(getCurrent()));
    }
}
