package graph;

import util.MathsPro;

import java.util.ArrayList;
import java.util.List;

public class RandomGraph {
    private RandomGraph() {//On empêche l'instanciation de la classe
    }

    /**
     * This method transforms a given graph into a random graph.
     *
     * @param graph The Graph object to fill.
     * @author Clément Bourles (with Quentin Roussel's idea)
     */
    public static void randomGraph(Graph graph) {
        List<Vertex> allVertexes = graph.getAllVertexes();
        final int numberNeighbors = graph.getNbSuccessors(allVertexes.get(0)) - 1;//On définit le nombre de voisins que doit avoir un sommet pour être visité

        for (int i = 0; i < allVertexes.size(); i++) {
            graph.setVertex(allVertexes.get(i), true);//On met tous les sommets à vide
        }
        allVertexes = graph.getAllVertexes();
        int rIndex = (MathsPro.random(allVertexes.size()));
        Vertex pivot = allVertexes.get(rIndex);
        explorer(graph, pivot, numberNeighbors);


        allVertexes = graph.getAllVertexes();
        for (int i = 1; i < allVertexes.size() - 1; i++) {//Fini d'enlever les espaces plein
            if (graph.getNbSuccessors(allVertexes.get(i)) >= 5) {
                explorer(graph, allVertexes.get(i), numberNeighbors);
            }
        }


        reverse(graph);//On inverse le graphe (on remplace les murs par des espaces vides)
    }

    /**
     * This method reverses a given graph.
     *
     * @param graph The Graph object to reverse.
     */
    public static void reverse(Graph graph) {
        List<Vertex> allVertexes = graph.getAllVertexes();
        for (int i = 0; i < allVertexes.size(); i++) {
            graph.setVertex(allVertexes.get(i), !allVertexes.get(i).isConnected());
        }
    }

    /**
     * This method explores a given graph starting from a specified pivot vertex and visits its neighbors recursively if they have at least a certain number of neighbors.
     * this is an implementation of a depth-first search algorithm.
     *
     * @param graph           The Graph object to explore.
     * @param pivot           The starting vertex of the exploration.
     * @param numberNeighbors The minimum number of neighbors that a vertex should have to be visited.
     */
    private static void explorer(Graph graph, Vertex pivot, int numberNeighbors) {

        graph.setVertex(pivot, false);//On transforme le pivot en mur (ça le marque)
        Vertex successor;
        if (graph.getNbSuccessors(pivot) >= numberNeighbors) {
            List<Integer> aleatSame = new ArrayList<>();
            for (int i = 0; i < graph.getSuccessors(pivot).size(); i++) {
                int r = MathsPro.random(graph.getSuccessors(pivot).size());

                while (aleatSame.contains(r)) {
                    r = MathsPro.random(graph.getSuccessors(pivot).size());//Permet de générer un nombre aléatoire différent à chaque fois
                }
                aleatSame.add(r);

                successor = graph.getSuccessors(pivot).get(r);
                if (successor.isConnected() && graph.getNbSuccessors(successor) >= numberNeighbors) {
                    explorer(graph, successor, numberNeighbors);
                }
            }
        }
    }
}
