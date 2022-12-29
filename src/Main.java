import Graph.*;
import Maze.*;
import ui.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Maze laby1 = new Maze(12,15);


        laby1.initBlank();
        //laby1.initFromTextFile("data/test.maze");
        new FrameUI(laby1);


    }
}