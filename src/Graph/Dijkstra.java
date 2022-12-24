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
        Vertex succVertex=pivot;

        System.out.println("Start : "+startVertex.getLabel());
        System.out.println("End : "+endVertex.getLabel());

        for (int i = 0; i < graph.getAllVertexes().size(); i++) {
            if(graph.getAllVertexes().get(i) != pivot)
                minDistance.set(graph.getAllVertexes().get(i), 999);
        }

        while (!(processVertex.isIncluded(endVertex))) {
            System.out.println("Pivot :"+pivot.getLabel());
            for (int i = 0; i < graph.getSuccessors(pivot).size(); i++) {
                succVertex = graph.getSuccessors(pivot).get(i);
                if (!(processVertex.isIncluded(succVertex))) {
                    Integer distTest = minDistance.minDistance(pivot) + graph.getDistance(pivot, succVertex);
                    if (distTest < minDistance.minDistance(succVertex)) {
                        minDistance.set(succVertex, distTest);
                        shortestPaths.add(succVertex, pivot);
                    }
                }
            }
            System.out.println("Distance pivot : "+minDistance.minDistance(pivot));
            System.out.println("Distance SuccVertex : "+minDistance.minDistance(succVertex));

            int distMin = 999;
            for (int i = 0; i < graph.getAllVertexes().size(); i++) {
                //System.out.println(minDistance.minDistance(graph.getAllVertexes().get(i)));
                if(minDistance.minDistance(graph.getAllVertexes().get(i))<= distMin && !processVertex.isIncluded(graph.getAllVertexes().get(i))){
                    pivot=graph.getAllVertexes().get(i);
                    distMin=minDistance.minDistance(graph.getAllVertexes().get(i));
                    System.out.println("Pivot tset : "+pivot.getLabel());
                }
            }
            processVertex.add(pivot);
        }



        return shortestPaths;
    }


}
