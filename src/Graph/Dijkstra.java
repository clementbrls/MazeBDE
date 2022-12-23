package Graph;

import Maze.Maze;

public class Dijkstra {

    public Dijkstra() {
    }

    public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) {
        ProcessedVertexesImpl processVertex = new ProcessedVertexesImpl();
        Vertex pivot;
        MinDistanceImpl minDistance = new MinDistanceImpl();
        ShortestPathsImpl shortestPaths = new ShortestPathsImpl();

        processVertex.add(startVertex);
        pivot = startVertex;
        minDistance.set(startVertex, 0);

        for (int i = 0; i < graph.getAllVertexes().size(); i++) {
            minDistance.set(graph.getAllVertexes().get(i), 999);
        }

        while (!(processVertex.isIncluded(endVertex))) {
            for (int i = 0; i < graph.getSuccessors(pivot).size(); i++) {
                Vertex succVertex = graph.getSuccessors(pivot).get(i);
                if (!(processVertex.isIncluded(succVertex))) {
                    Integer distTest = minDistance.minDistance(pivot) + graph.getDistance(pivot, succVertex);
                    if (distTest < minDistance.minDistance(succVertex)) {
                        minDistance.set(succVertex, distTest);
                        shortestPaths.add(succVertex, pivot);
                    }
                }
            }

            int distMin = 999;
            for (int i = 0; i < graph.getAllVertexes().size(); i++) {
                if(minDistance.minDistance(graph.getAllVertexes().get(i))<distMin && !processVertex.isIncluded(graph.getAllVertexes().get(i))){
                    pivot=graph.getAllVertexes().get(i);
                    distMin=minDistance.minDistance(graph.getAllVertexes().get(i));
                }
            }
            processVertex.add(pivot);
        }



        return null;
    }


}
