package graph;

/**
 * Interface for a Path of vertexes
 */
public interface VertexPath {
    /**
     * add a vertex to the path
     *
     * @param vertex
     */
    void add(Vertex vertex);

    /**
     * get the total distance of the path
     *
     * @return the distance of the path
     */
    int getDistance();

    /**
     * get the vertex at the given index
     *
     * @param i the index
     * @return the vertex at the given index
     */
    Vertex get(int i);

    /**
     * @return true if it is a path, false if it is not a path (so if it's empty)
     */
    boolean isPath();

    /**
     * @param vertex
     * @return true if the vertex is included in the path, false if not
     */
    boolean isIncluded(Vertex vertex);
}
