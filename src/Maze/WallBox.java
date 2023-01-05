package Maze;

public class WallBox extends MazeBox {
    public static final char Label = 'W';
    public WallBox(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isWall() {
        return true;
    }
}
