package graph_editor.graph;

// Only for this package
class EdgeImpl implements Edge {
    EdgeImpl(Vertex source, Vertex target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public Vertex getSource() {
        return source;
    }

    @Override
    public Vertex getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge other = (Edge)o;
        return getSource().equals(other.getSource()) && 
            getTarget().equals(other.getTarget());
    }

    private Vertex source;
    private Vertex target;
}