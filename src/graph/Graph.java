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
     * @param vertex the vertex to test
     * @return the list of all vertexes that are successors of the given vertex
     */
    List<Vertex> getSuccessors(Vertex vertex);

    /**
     * The distance between 2 neighbors Vertexes
     *
     * @param src the source vertex
     * @param dst the destination vertex
     * @return the distance between the two given vertexes
     */
    int getDistance(Vertex src, Vertex dst);

    /**
     * This method sets the given vertex to connected to the graph or not
     *
     * @param vert      the vertex to set
     * @param connected true if the vertex is connected to the graph, false if not
     */
    void setVertex(Vertex vert, boolean connected);

    /**
     * @param vert the vertex to test
     * @return the number of theoretical successors of the given vertex (it needs to also count the vertexes that doesn't exist : that are outside the borders)
     */
    int getNbSuccessors(Vertex vert);
}
