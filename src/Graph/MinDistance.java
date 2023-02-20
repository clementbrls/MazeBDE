package Graph;

/**
 * Interface for the distance min. from a Vertex
 */
public interface MinDistance {
    /**
     * @param vert The vertex
     * @return the distance min. from the given vertex
     */
    int minDistance(Vertex vert);

    /**
     * set the distance min. from a Vertex
     * @param vert The vertex
     * @param dist The distance min. from the given vertex
     */
    void set(Vertex vert, Integer dist);
}
