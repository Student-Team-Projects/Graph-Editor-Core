package graph_editor.graph;

import java.util.ArrayList;
import java.util.List;

public class VersionStackImpl<T> implements VersionStack<T> {
    private final List<T> stack;
    private int pointer;

    public VersionStackImpl(T initial) {
        this.stack = new ArrayList<>();
        stack.add(initial);
        this.pointer = 0;
    }

    public void undo() {
        pointer = Math.max(pointer - 1, 0);
    }

    public void redo() {
        pointer = Math.min(pointer + 1, stack.size() - 1);
    }

    public void push(T element) {
        unwind();
        stack.add(element);
    }

    public boolean isUndoPossible() {
        return pointer != 0;
    }
    public boolean isRedoPossible() {
        return pointer != stack.size() - 1;
    }
    public T getCurrent() { return stack.get(pointer); }
    public Iterable<T> getStack() { return stack; }

    private void unwind() {
        while(stack.size() - 1 > pointer) {
            stack.remove(stack.size() - 1);
        }
    }
}
