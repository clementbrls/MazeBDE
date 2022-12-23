package Maze;
import java.io.*;
import Graph.*;
public class Main {
    public static void main(String[] args) {


        Maze laby1 = new Maze();
        laby1.initFromTextFile("data/laby.maze");
        System.out.println("test");
        ShortestPaths dijkstra = Dijkstra.dijkstra(laby1, laby1.getAllVertexes().get(1), laby1.getAllVertexes().get(60));


        System.out.println(dijkstra.toString());

    }
}