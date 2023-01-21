package graph_editor.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphStackImpl implements GraphStack {
    private final List<Graph> stack;
    private int pointer;

    public GraphStackImpl(Graph initial) {
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

    public void push(Graph graph) {
        unwind();
        stack.add(graph);
    }

    public boolean isUndoPossible() {
        return pointer != 0;
    }
    public boolean isRedoPossible() {
        return pointer != stack.size() - 1;
    }
    public Graph getCurrentGraph() { return stack.get(pointer); }
    public Iterable<Graph> getGraphStack() { return stack; }

    private void unwind() {
        while(stack.size() - 1 > pointer) {
            stack.remove(stack.size() - 1);
        }
    }
}
