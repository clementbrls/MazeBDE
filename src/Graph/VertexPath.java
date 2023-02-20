package Graph;

/**
 * Interface for a Path of vertexes
 */
public interface VertexPath {
    /**
     * add a vertex to the path
     * @param vertex
     */
    public void add(Vertex vertex);

    /**
     * get the total distance of the path
     * @return the distance of the path
     */
    public int getDistance();

    /**
     * get the vertex at the given index
     * @param i the index
     * @return the vertex at the given index
     */
    public Vertex get(int i);

    /**
     * @return true if it is a path, false if it is not a path (so if it's empty)
     */
    public boolean isPath();

    /**
     * @param vertex
     * @return true if the vertex is included in the path, false if not
     */
    public boolean isIncluded(Vertex vertex);
}
