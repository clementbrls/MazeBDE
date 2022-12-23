package Maze;
import Graph.Graph;
import Graph.Vertex;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Maze implements Graph{
    private MazeBox[][] maze;

    public Maze(){
        maze = new MazeBox[10][10];
    }


    public final void initFromTextFile(String fileName){
        try {
            FileReader fr=new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            for (int i=0; i<10;i++){
                String line=br.readLine();
                for(int u=0; u<10;u++){

                    switch(line.charAt(u)){//Création de chaque cellule du labyrinthe en fonction du fichier.
                        case 'E':
                            maze[i][u]= new EmptyBox(i,u);
                            break;
                        case 'W':
                            maze[i][u]= new WallBox(i,u);
                            break;
                        case 'A':
                            maze[i][u] = new ArrivalBox(i,u);
                            break;
                        case 'D':
                            maze[i][u] = new DepartureBox(i,u);
                            break;
                    }
                }
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }


    }

    public ArrayList<Vertex> getAllVertexes(){
        allVertex = new ArrayList<Vertex>();
        for(int i=0;i<10;i++){
            for(int u=0;u<10;u++){
                if( !(maze[i][u] instanceof WallBox)) {
                    allVertex.add(maze[i][u]);
                }
            }
        }
        return allVertex;
    }

    public ArrayList<Vertex> getSuccessors(Vertex vertex){
        MazeBox box = (MazeBox) vertex ;
        int line = box.getLine();
        int column = box.getColumn();
        successors = new ArrayList<Vertex>();


        if(line != 0) {
            successors.add(maze[line-1][column]);}
        if(line != 9){
            successors.add(maze[line+1][column]);}
        if(column != 0){
            successors.add(maze[line][column-1]);}
        if(column != 9){
            successors.add(maze[line][column+1]);}

        if(line%2==0) {
            if(line != 0 && column !=0){
                successors.add(maze[line-1][column-1]);}
            if(line !=9 && column !=0){
                successors.add(maze[line+1][column-1]);}
        } else {
            if(line !=0 && column !=9){
                successors.add(maze[line-1][column+1]);}
            if(line !=9 && column !=9){
                successors.add(maze[line+1][column+1]);}
        }

        return succcessors;
    }

    public int getDistance(Vertex src,Vertex dst){
        return 1;
    }

}
