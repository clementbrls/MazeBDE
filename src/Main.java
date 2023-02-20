import maze.*;
import ui.*;

public class Main {
    public static void main(String[] args) {
        Maze laby1 = new Maze();
        try {
            laby1.initFromTextFile("data/laby.maze");
        } catch (MazeReadingException e) {
            throw new RuntimeException(e);
        }
        new FrameUI(laby1);
    }
}