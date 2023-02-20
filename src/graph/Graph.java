package graph;

import java.util.List;

/**
 * Interface for a Graph
 */
public interface Graph {
    /**
     * @return the list of all vertexes in the graph
     */
    List<Vertex> getAllVertexes();

    /**
     * @param vertex
     * @return the list of all vertexes that are successors of the given vertex
     */
    List<Vertex> getSuccessors(Vertex vertex);

    /**
     * The distance between 2 neighbors Vertexes
     *
     * @param src
     * @param dst
     * @return the distance between the two given vertexes
     */
    int getDistance(Vertex src, Vertex dst);
}
