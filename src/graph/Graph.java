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

    /**
     * This method sets the given vertex to empty or not (so it is connex with the graph or not)
     *
     * @param vert
     * @param empty
     */
    void setVertex(Vertex vert, boolean empty);

    /**
     * @param vert
     * @return the number of successors of the given vertex, it needs to also count the vertexes that doesn't exit : that are outside the borders
     */
    int getNbSuccessors(Vertex vert);
}
