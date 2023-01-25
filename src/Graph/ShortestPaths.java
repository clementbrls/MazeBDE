package Graph;

public interface ShortestPaths {
    Vertex previous(Vertex vert);
    void add(Vertex vert1, Vertex vert2);
    VertexPath getPath();
}
