package Maze;

public class ArrivalBox extends EmptyBox {
    public static final char Label = 'A';

    public ArrivalBox(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isArrival() {
        return true;
    }

}
