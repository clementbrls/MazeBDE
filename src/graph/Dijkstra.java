package graph;

import java.util.List;

public class Dijkstra {

    private Dijkstra() {//Private constructor to prevent instantiation of this class.
    }

    /**
     * This method is the implementation of the Dijkstra algorithm
     *
     * @param graph       the graph to use
     * @param startVertex the start vertex
     * @param endVertex   the end vertex
     * @return the shortest path between the startVertex and the endVertex
     */

    public static VertexPath dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) {
        ProcessedVertexesImpl processVertex = new ProcessedVertexesImpl();
        Vertex pivot;
        MinDistance minDistance = new MinDistanceImpl();
        ShortestPaths shortestPaths = new ShortestPathsImpl(startVertex, endVertex);

        processVertex.add(startVertex);
        pivot = startVertex;
        minDistance.set(startVertex, 0);
        Vertex succVertex;
        List<Vertex> graphAllVertexes = graph.getAllVertexes();

        for (int i = 0; i < graphAllVertexes.size(); i++) {
            if (graphAllVertexes.get(i) != pivot)
                minDistance.set(graphAllVertexes.get(i), Integer.MAX_VALUE);
        }
        boolean noPath = false;
        while (!processVertex.isIncluded(endVertex) && !noPath) {
            for (int i = 0; i < graph.getSuccessors(pivot).size(); i++) {
                succVertex = graph.getSuccessors(pivot).get(i);
                if (!(processVertex.isIncluded(succVertex))) {
                    int distTest = minDistance.minDistance(pivot) + graph.getDistance(pivot, succVertex);
                    if (distTest < minDistance.minDistance(succVertex)) {
                        minDistance.set(succVertex, distTest);
                        shortestPaths.add(succVertex, pivot);
                    }
                }
            }
            int distMin = Integer.MAX_VALUE;
            noPath = true; //Par défaut noPath est vrai
            for (int i = 0; i < graphAllVertexes.size(); i++) {//Récup le sommet le plus proche
                if (minDistance.minDistance(graphAllVertexes.get(i)) < distMin && !processVertex.isIncluded(graphAllVertexes.get(i))) {
                    pivot = graphAllVertexes.get(i);
                    noPath = false;//tant qu'on arrive a trouvé un autre sommet qui n'est pas déjà dans processVertex, noPath est faux, et donc on continue la boucle
                    distMin = minDistance.minDistance(graphAllVertexes.get(i));
                }
            }
            processVertex.add(pivot);
        }


        VertexPath path;
        if (!noPath) {
            path = shortestPaths.getPath();
            //System.out.println("Distance : " + minDistance.minDistance(endVertex));
        } else {
            path = new DijsktraPath(true);
        }
        return path;
    }


}
