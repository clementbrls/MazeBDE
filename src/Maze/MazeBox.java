package Maze;

public abstract class MazeBox {
    private int line;
    private int column;

    public MazeBox(int x, int y) {
        line = x;
        column = y;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
