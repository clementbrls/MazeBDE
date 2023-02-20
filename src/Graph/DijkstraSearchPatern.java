package Graph;

import java.util.Iterator;

/**
 * Permet de définir un itérateur pour les sommets traités par l'algorithme de Dijkstra
 * et ainsi par exemple montrer une animation de l'algorithme de Dijkstra
 */
public interface DijkstraSearchPatern {
    void setIterator(Iterator<Vertex> iterator);
    Iterator<Vertex> getIterator();
}
