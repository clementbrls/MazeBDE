package Maze;

public class ArrivalBox extends EmptyBox {

    public ArrivalBox(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isArrival() {
        return true;
    }
}
