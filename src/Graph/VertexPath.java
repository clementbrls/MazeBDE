package Graph;

import java.util.ArrayList;

public class VertexPath {
    private ArrayList<Vertex> path;

    public VertexPath(){
        path = new ArrayList<Vertex>();
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
        String txt=this.get(0).getLabel();
        for(int i=1;i<this.size();i++){
            txt=this.get(i).getLabel()+" => "+txt;
        }
        return txt;
    }

    public int getDistance(){
        int distance;
        if(path.size()==0){
            distance=999;
        } else {
            distance=path.size()-1;
        }
        return distance;
    }
}
