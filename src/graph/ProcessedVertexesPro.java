package graph;

import java.util.HashSet;
import java.util.Iterator;

public class ProcessedVertexesPro implements ProcessedVertexes {
    private final HashSet<Vertex> list;

    public ProcessedVertexesPro() {
        list = new HashSet<Vertex>();
    }

    public boolean isIncluded(Vertex vert) {
        return list.contains(vert);
    }

    public void add(Vertex vert) {
        list.add(vert);
    }

    public Iterator<Vertex> getIterator() {
        return list.iterator();
    }
}