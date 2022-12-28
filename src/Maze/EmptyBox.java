package Maze;

public class EmptyBox extends MazeBox {

    public EmptyBox(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
