package Graph;

import java.util.HashMap;

public class MinDistanceImpl implements MinDistance{
    private final HashMap<Vertex, Integer> hashMap;

    public MinDistanceImpl(){
        hashMap = new HashMap<Vertex, Integer>();
    }

    public void set(Vertex vert, Integer dist){
        hashMap.put(vert,dist);
    }


    public int minDistance(Vertex vert) {
        return hashMap.get(vert);
    }
}
