import Graph.*;
import Maze.*;
import ui.*;

public class Main {
    public static void main(String[] args) {

        new FrameUI();
        Maze laby1 = new Maze();
        laby1.initFromTextFile("data/laby.maze");
        laby1.showToConsole();

        // dijkstra = Dijkstra.dijkstra(laby1, laby1.getAllVertexes().get(1), laby1.getAllVertexes().get(60));

        ShortestPaths test = laby1.dijkstra();


        System.out.println(test);

    }
}