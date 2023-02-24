import maze.*;
import ui.*;

public class Main {
    public static void main(String[] args) {
        Maze laby1 = new Maze(15, 25);
        laby1.randomize();
        new FrameUI(laby1);
    }
}