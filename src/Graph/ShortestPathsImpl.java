package Graph;

import java.util.ArrayList;
import java.util.HashMap;

public class ShortestPathsImpl implements ShortestPaths{

    private HashMap<Vertex,Vertex> hashMap;
    private Vertex startVertex;
    private Vertex endVertex;

    private ArrayList<Vertex> path;

    public String toString() {
        String txt= String.valueOf(endVertex.getLabel());
        Vertex oldV = endVertex;
        while(oldV != startVertex) {
            txt = previous(oldV).getLabel() + " => " + txt;
            oldV=previous(oldV);

        }
        return txt;
    }

    public ShortestPathsImpl(Vertex startVertex, Vertex endVertex){
        this.startVertex=startVertex;
        this.endVertex=endVertex;
        hashMap = new HashMap<Vertex, Vertex>();
    }

    public void add(Vertex vert1, Vertex vert2){
        hashMap.put(vert1,vert2);
    }

    public Vertex previous(Vertex vert) {
        return hashMap.get(vert);
    }

    public ArrayList<Vertex> getPath(){
        path= new ArrayList<>();
        Vertex oldV = endVertex;
        while(oldV != startVertex) {
            path.add(oldV);
            oldV=previous(oldV);
        }
        return path;
    }
}
