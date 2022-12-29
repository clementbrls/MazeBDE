package Graph;

public class Dijkstra {

    public Dijkstra() {
    }

    public static VertexPath dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) {
        ProcessedVertexes processVertex = new ProcessedVertexesImpl();
        Vertex pivot;
        MinDistance minDistance = new MinDistanceImpl();
        ShortestPaths shortestPaths = new ShortestPathsImpl(startVertex,endVertex);

        processVertex.add(startVertex);
        pivot = startVertex;
        minDistance.set(startVertex, 0);
        Vertex succVertex=pivot;

        for (int i = 0; i < graph.getAllVertexes().size(); i++) {
            if(graph.getAllVertexes().get(i) != pivot)
                minDistance.set(graph.getAllVertexes().get(i), 999);
        }

        while (!(processVertex.isIncluded(endVertex))) {
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
            for (int i = 0; i < graph.getAllVertexes().size(); i++) {
                //System.out.println(minDistance.minDistance(graph.getAllVertexes().get(i)));
                if(minDistance.minDistance(graph.getAllVertexes().get(i))<= distMin && !processVertex.isIncluded(graph.getAllVertexes().get(i))){
                    pivot=graph.getAllVertexes().get(i);
                    distMin=minDistance.minDistance(graph.getAllVertexes().get(i));
                }
            }
            processVertex.add(pivot);
        }

        VertexPath path = shortestPaths.getPath();
        System.out.println("Distance : "+minDistance.minDistance(endVertex));
        return path;
    }


}
