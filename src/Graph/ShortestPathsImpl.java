package Graph;

import java.util.HashMap;

public class ShortestPathsImpl implements ShortestPaths{
    public String toString() {
        return "ShortestPathsImpl{" +
                "hashMap=" + hashMap +
                '}';
    }

    private HashMap<Vertex,Vertex> hashMap;

    public ShortestPathsImpl(){
        hashMap = new HashMap<Vertex, Vertex>();
    }

    public void add(Vertex vert1, Vertex vert2){
        hashMap.put(vert1,vert2);
    }

    public Vertex previous(Vertex vert) {
        return hashMap.get(vert);
    }
}
