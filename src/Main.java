import Graph.*;
import Maze.*;
import ui.*;

public class Main {
    public static void main(String[] args) {
        Maze laby1 = new Maze();
        new FrameUI(laby1);

        laby1.initFromTextFile("data/laby2.maze");

        laby1.showToConsole();

        // dijkstra = Dijkstra.dijkstra(laby1, laby1.getAllVertexes().get(1), laby1.getAllVertexes().get(60));

        ShortestPaths test = laby1.dijkstra();


        System.out.println(test);

    }
}