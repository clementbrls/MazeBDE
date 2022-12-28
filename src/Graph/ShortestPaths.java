package Graph;

import java.util.ArrayList;

public interface ShortestPaths {
    public Vertex previous(Vertex vert);
    public String toString();
    public void add(Vertex vert1, Vertex vert2);
    public ArrayList<Vertex> getPath();
}
