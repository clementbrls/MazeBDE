package maze;

import graph.Vertex;

import java.awt.*;

public abstract class MazeBox implements Vertex {
    private final int line;
    private final int column;


    public MazeBox(int x, int y) {
        line = x;
        column = y;
    }

    /**
     * @return the line number of the box
     */
    public int getLine() {
        return line;
    }

    /**
     * @return the column number of the box
     */
    public int getColumn() {
        return column;
    }

    public String toString() {
        return "" + line + ":" + column;
    }

    /**
     * @return true if the box is a wall
     */
    public boolean isWall() {
        return false;
    }

    /**
     * @return true if the box is empty
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * @return true if the box is connected to the maze (so if it's not a wall)
     */
    @Override
    public boolean isConnected() {
        return isEmpty();
    }

    /**
     * @return true if the box is a departure
     */
    public boolean isDeparture() {
        return false;
    }

    /**
     * @return true if the box is an arrival
     */
    public boolean isArrival() {
        return false;
    }

    /**
     * @return the suggested color for the box
     */
    public abstract Color getColor();//Ceci ne respecte la séparation model/vue, car une mazeBox simplement propose une couleur, mais ne la dessine pas

}
