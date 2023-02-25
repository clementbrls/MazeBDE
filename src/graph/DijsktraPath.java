package graph;

import java.util.ArrayList;
import java.util.Iterator;

public class DijsktraPath implements VertexPath {
    private final ArrayList<Vertex> path;
    private Boolean noPath = false;
    private Iterator<Vertex> pathIterator = null;

    public DijsktraPath() {
        noPath = false;
        path = new ArrayList<Vertex>();
    }

    public DijsktraPath(boolean noPath) {
        path = new ArrayList<Vertex>();
        this.noPath = noPath;
    }

    public void add(Vertex vertex) {
        path.add(vertex);
    }

    public Vertex get(int i) {
        return path.get(i);
    }

    public String toString() {
        String txt = this.get(0).toString();
        for (int i = 1; i < path.size(); i++) {
            txt = this.get(i).toString() + " => " + txt;
        }
        return txt;
    }

    public int getDistance() {
        int distance;
        if (noPath) {
            distance = 9999;
        } else {
            distance = path.size() - 1;//On enlève 1, car on ne compte pas le premier sommet
        }
        return distance;
    }

    public boolean isIncluded(Vertex vertex) {
        return path.contains(vertex);
    }

    public boolean isPath() {
        return !noPath;
    }
}