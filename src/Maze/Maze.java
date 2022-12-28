package Maze;

import Graph.*;

import java.io.*;
import java.util.ArrayList;

public class Maze implements Graph {
    private MazeBox[][] maze;
    private MazeBox arrival;
    private MazeBox departure;
    private Path solveMaze;


    public Maze() {
        maze = new MazeBox[10][10];
    }


    public MazeBox getMazeBox(int line, int column){
        return maze[line][column];
    }
    public final void initFromTextFile(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < 10; i++) {
                String line = br.readLine();
                for (int u = 0; u < 10; u++) {

                    maze[i][u] = switch (line.charAt(u)) {//Création de chaque cellule du labyrinthe en fonction du fichier.
                        case 'E':
                             yield new EmptyBox(i, u);
                        case 'W':
                            yield new WallBox(i, u);
                        case 'A':
                            yield new ArrivalBox(i, u);
                        case 'D':
                            yield new DepartureBox(i, u);
                        default:
                            throw new IllegalStateException("Unexpected value: " + line.charAt(u));
                    };
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
                if (maze[i][u].isEmpty()) {
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
            MazeBox succ = (MazeBox) successors.get(i);
            if (succ.isWall()) {
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

    public MazeBox getArrival(){
        arrival=null;
        for(int i=0;i<maze.length;i++){
            for(int u=0;u<maze[0].length;u++){
                if(maze[i][u].isArrival()) arrival=maze[i][u];
            }
        }
        return arrival;
    }

    public MazeBox getDeparture(){
        departure=null;
        for(int i=0;i<maze.length;i++){
            for(int u=0;u<maze[0].length;u++){
                if(maze[i][u].isDeparture()) departure=maze[i][u];
            }
        }
        return departure;
    }

    public Path dijkstra(){
        getDeparture();
        getArrival();

        Graph graph = this;
        this.solveMaze = Dijkstra.dijkstra(graph,departure,arrival);
        return solveMaze;
    }

    public void showToConsole(){
        for(int i=0;i<10;i++){
            if(i%2 != 0)System.out.print(" ");
            for(int u=0;u<10;u++){
                String color;
                if(maze[i][u].isDeparture()) color = "\u001B[34m";
                else if(maze[i][u].isArrival()) color = "\u001B[36m";
                else if(maze[i][u].isWall()) color = "\u001B[30m";
                else color = "\u001B[0m";
                System.out.print(color+""+maze[i][u].getLabel()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
