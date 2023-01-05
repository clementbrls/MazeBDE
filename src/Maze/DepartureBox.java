package Maze;

public class DepartureBox extends EmptyBox {
    public static final char Label = 'D';
    public DepartureBox(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isDeparture() {
        return true;
    }
}

