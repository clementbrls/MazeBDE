package Graph;

public interface ShortestPaths {
    public Vertex previous(Vertex vert);
    public void add(Vertex vert1, Vertex vert2);
    public VertexPath getPath();
}
