package Graph;

import java.util.List;

public interface Graph {
    List<Vertex> getAllVertexes() ;
    List<Vertex> getSuccessors(Vertex vertex) ;
    int getDistance(Vertex src, Vertex dst);
}
