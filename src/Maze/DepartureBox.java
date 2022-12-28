package Maze;

public class DepartureBox extends EmptyBox {

    public DepartureBox(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isDeparture() {
        return true;
    }
}

