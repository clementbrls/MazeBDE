package graph;

import java.util.ArrayList;
import java.util.List;

public class RandomGraph {

    public static Graph randomGraph(Graph graph) {
        List<Vertex> allVertexes = graph.getAllVertexes();
        final int numberNeighbors = graph.getNbSuccessors(allVertexes.get(0))-1;

        for (int i = 0; i < allVertexes.size(); i++) {
            graph.setVertex(allVertexes.get(i), true);
        }
        allVertexes = graph.getAllVertexes();
        int rIndex = (int) (Math.random() * allVertexes.size());
        Vertex pivot = allVertexes.get(rIndex);
        explorer(graph, pivot,numberNeighbors);


            allVertexes = graph.getAllVertexes();
            for (int i = 1; i < allVertexes.size()-1; i++) {//Fini d'enlever les espaces plein
                if (graph.getNbSuccessors(allVertexes.get(i)) >= 5) {
                    explorer(graph, allVertexes.get(i),numberNeighbors);
                }
            }


        reverse(graph);


        return graph;
    }

    public static void reverse(Graph graph) {
        List<Vertex> allVertexes = graph.getAllVertexes();
        for (int i = 0; i < allVertexes.size(); i++) {
            graph.setVertex(allVertexes.get(i), !allVertexes.get(i).isEmpty());
        }
    }

    private static void explorer(Graph graph, Vertex pivot,int numberNeighbors) {

        graph.setVertex(pivot, false);
        Vertex successor;
        if (graph.getNbSuccessors(pivot) >= numberNeighbors) {
            List<Integer> aleatSame = new ArrayList<>();
            for (int i = 0; i < graph.getSuccessors(pivot).size(); i++) {
                int r = (int) (Math.random() * graph.getSuccessors(pivot).size());

                while (aleatSame.contains(r)) {
                    r = (int) (Math.random() * graph.getSuccessors(pivot).size());
                }
                aleatSame.add(r);

                successor = graph.getSuccessors(pivot).get(r);
                if (successor.isEmpty() && graph.getNbSuccessors(successor) >= numberNeighbors) {
                    explorer(graph, successor,numberNeighbors);
                }
            }
        }
    }
}
