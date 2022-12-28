package Graph;

import java.util.ArrayList;

public class Path {
    private ArrayList<Vertex> path;

    public Path(){
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
}
