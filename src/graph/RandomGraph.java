package graph;

import java.util.List;

public class RandomGraph {

    public static Graph randomGraph(Graph graph){
        List<Vertex> allVertexes = graph.getAllVertexes();
        //ProcessedVertexes processVertex = new ProcessedVertexesPro();
        for(int i =0;i< allVertexes.size();i++){
            graph.setVertex(allVertexes.get(i),true);
        }
        allVertexes = graph.getAllVertexes();
        int rIndex = (int) (Math.random()* allVertexes.size());
        Vertex pivot = allVertexes.get(rIndex);
        explorer(graph,pivot);







        return graph;
    }

    public static Graph reverse(Graph graph){
        List<Vertex> allVertexes = graph.getAllVertexes();
        for(int i=0;i< allVertexes.size();i++){
            graph.setVertex(allVertexes.get(i), !allVertexes.get(i).isEmpty());
        }
        return graph;
    }

    private static void explorer(Graph graph,Vertex vert){
        final int numberNeighbors = 5;
        Vertex pivot = vert;
        graph.setVertex(pivot, false);
        if(graph.getSuccessors(pivot).size() >= numberNeighbors)
            for(int i=0;i<graph.getSuccessors(pivot).size();i++){
                if(graph.getSuccessors(pivot).get(i).isEmpty()) {
                    explorer(graph, graph.getSuccessors(pivot).get(i));
                }
            }


    }
}
