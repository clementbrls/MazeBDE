package Graph;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    public Dijkstra() {
    }

    public static VertexPath dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) {
        long timestart = System.currentTimeMillis();
        ProcessedVertexes processVertex = new ProcessedVertexesImpl();
        Vertex pivot;
        MinDistance minDistance = new MinDistanceImpl();
        ShortestPaths shortestPaths = new ShortestPathsImpl(startVertex,endVertex);

        if(startVertex==null) System.out.println("null");
        processVertex.add(startVertex);
        pivot = startVertex;
        minDistance.set(startVertex, 0);
        Vertex succVertex=pivot;
        List<Vertex> graphAllVertexes = graph.getAllVertexes();

        for (int i = 0; i < graphAllVertexes.size(); i++) {
            if(graphAllVertexes.get(i) != pivot)
                minDistance.set(graphAllVertexes.get(i), 999);
        }
        boolean noPath=false;
        while (!processVertex.isIncluded(endVertex) && !noPath) {
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
            int distMin = 999;
            noPath=true;
            for (int i = 0; i < graphAllVertexes.size(); i++) {
                if(minDistance.minDistance(graphAllVertexes.get(i))< distMin && !processVertex.isIncluded(graphAllVertexes.get(i))){
                    pivot=graphAllVertexes.get(i);
                    noPath=false;
                    distMin=minDistance.minDistance(graphAllVertexes.get(i));
                }
            }
            processVertex.add(pivot);
        }
        long timeend = System.currentTimeMillis();
        System.out.println("Dijkstra time: " + (timeend - timestart) + " ms");

        System.out.println("Distance : "+minDistance.minDistance(endVertex));
        VertexPath path= new VertexPath();
        if(!noPath) {
            path = shortestPaths.getPath();
        } else {
            path = new VertexPath(true);
        }
        return path;
    }


}
