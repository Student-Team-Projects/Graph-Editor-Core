package graph_editor.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphStack {
    private final List<Graph> stack;
    private int pointer;

    public GraphStack(Graph initial) {
        this.stack = new ArrayList<>();
        stack.add(initial);
        this.pointer = 0;
    }

    public Graph undo() {
        pointer = Math.max(pointer - 1, 0);
        return getCurrentGraph();
    }

    public Graph redo() {
        pointer = Math.min(pointer + 1, stack.size() - 1);
        return getCurrentGraph();
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
    public List<Graph> getGraphStack() { return stack; }

    private void unwind() {
        while(stack.size() - 1 > pointer) {
            stack.remove(stack.size() - 1);
        }
    }
}
