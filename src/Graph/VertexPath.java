package Graph;

import java.util.ArrayList;

public class VertexPath {
    private final ArrayList<Vertex> path;
    private Boolean noPath=false;

    public VertexPath(){
        path = new ArrayList<Vertex>();
    }
    public VertexPath(boolean noPath){
        path = new ArrayList<Vertex>();
        this.noPath=noPath;
    }

    public void add(Vertex vertex){
        path.add(vertex);
    }

    public Vertex get(int i){
        return path.get(i);
    }

    public int size(){
        return path.size();
    }

    public String toString() {
        String txt=this.get(0).toString();
        for(int i=1;i<path.size();i++){
            txt=this.get(i).toString()+" => "+txt;
        }
        return txt;
    }

    public int getDistance(){
        int distance;
        if(noPath){
            distance=9999;
        } else {
            distance=path.size()-1;
        }
        return distance;
    }

    public boolean isIncluded(Vertex vertex){
        return path.contains(vertex);
    }

    public boolean isPath(){
        return !noPath;
    }

}
