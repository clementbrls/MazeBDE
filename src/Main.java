import Graph.*;
import Maze.*;
import ui.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Maze laby1 = new Maze();
        new FrameUI(laby1);

        laby1.initFromTextFile("data/laby.maze");

        laby1.showToConsole();

        // dijkstra = Dijkstra.dijkstra(laby1, laby1.getAllVertexes().get(1), laby1.getAllVertexes().get(60));

        Path test = laby1.dijkstra();


        System.out.println(test);

    }
}