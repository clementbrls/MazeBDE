import Graph.*;
import Maze.*;
import ui.*;

public class Main {
    public static void main(String[] args) {
        Maze laby1 = new Maze();

        // laby1.initBlank();
        try {
            laby1.initFromTextFile("data/laby.maze");
        } catch (MazeReadingException e) {
            throw new RuntimeException(e);
        }
        // laby1.initRandom();
        new FrameUI(laby1);
    }
}