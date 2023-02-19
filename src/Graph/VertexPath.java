package Graph;

public interface VertexPath {
    public void add(Vertex vertex);
    public int getDistance();
    public Vertex get(int i);
    public boolean isPath();
    public boolean isIncluded(Vertex vertex);
}
