package Maze;

import Graph.*;

import java.io.*;
import java.util.ArrayList;

public class Maze implements Graph {
    private MazeBox[][] maze;

    public Maze() {
        maze = new MazeBox[10][10];
    }


    public final void initFromTextFile(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < 10; i++) {
                String line = br.readLine();
                for (int u = 0; u < 10; u++) {

                    switch (line.charAt(u)) {//Création de chaque cellule du labyrinthe en fonction du fichier.
                        case 'E':
                            maze[i][u] = new EmptyBox(i, u);
                            break;
                        case 'W':
                            maze[i][u] = new WallBox(i, u);
                            break;
                        case 'A':
                            maze[i][u] = new ArrivalBox(i, u);
                            break;
                        case 'D':
                            maze[i][u] = new DepartureBox(i, u);
                            break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public final void saveToTextFile(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            PrintWriter pw = new PrintWriter(fos);

            for (int i = 0; i < 10; i++) {
                for (int u = 0; u < 10; u++) {
                    if (maze[i][u] instanceof EmptyBox)
                        pw.print("E");
                    if (maze[i][u] instanceof WallBox)
                        pw.print('W');
                    if (maze[i][u] instanceof ArrivalBox)
                        pw.print('A');
                    if (maze[i][u] instanceof DepartureBox)
                        pw.print('D');
                }
                pw.println();
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Vertex> getAllVertexes() {
        ArrayList<Vertex> allVertex = new ArrayList<Vertex>();
        for (int i = 0; i < 10; i++) {
            for (int u = 0; u < 10; u++) {
                if (!(maze[i][u] instanceof WallBox)) {
                    allVertex.add(maze[i][u]);
                }
            }
        }
        return allVertex;
    }

    public ArrayList<Vertex> getSuccessors(Vertex vertex) {
        MazeBox box = (MazeBox) vertex;
        int line = box.getLine();
        int column = box.getColumn();
        ArrayList<Vertex> successors = new ArrayList<Vertex>();


        if (line != 0) {
            successors.add(maze[line - 1][column]);
        }
        if (line != 9) {
            successors.add(maze[line + 1][column]);
        }
        if (column != 0) {
            successors.add(maze[line][column - 1]);
        }
        if (column != 9) {
            successors.add(maze[line][column + 1]);
        }

        if (line % 2 == 0) {
            if (line != 0 && column != 0) {
                successors.add(maze[line - 1][column - 1]);
            }
            if (line != 9 && column != 0) {
                successors.add(maze[line + 1][column - 1]);
            }
        } else {
            if (line != 0 && column != 9) {
                successors.add(maze[line - 1][column + 1]);
            }
            if (line != 9 && column != 9) {
                successors.add(maze[line + 1][column + 1]);
            }
        }

        //Suppression des murs en tant que successeurs
        int i = 0;
        while (i < successors.size()) {
            if (successors.get(i) instanceof WallBox) {
                successors.remove(i);
            } else {
                i++;
            }
        }

        return successors;
    }

    public int getDistance(Vertex src, Vertex dst) {
        return 1;
    }

    public ShortestPaths dijkstra(){
        Vertex arrival=null;
        Vertex departure = null;
        for(int i=0;i<getAllVertexes().size();i++){
            if(getAllVertexes().get(i) instanceof DepartureBox){
                departure=getAllVertexes().get(i);
            }
            if(getAllVertexes().get(i) instanceof ArrivalBox) {
                arrival = getAllVertexes().get(i);
            }
        }
        Graph graph = (Graph) this;
        return Dijkstra.dijkstra(graph,departure,arrival);
    }

    public void showToConsole(){
        for(int i=0;i<10;i++){
            if(i%2 != 0)System.out.print(" ");
            for(int u=0;u<10;u++){
                String color;
                if(maze[i][u] instanceof DepartureBox) color = "\u001B[34m";
                else if(maze[i][u] instanceof ArrivalBox) color = "\u001B[36m";
                else if(maze[i][u] instanceof WallBox) color = "\u001B[30m";
                else color = "\u001B[0m";
                System.out.print(color+""+maze[i][u].getLabel()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
