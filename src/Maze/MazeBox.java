package Maze;

import Graph.Vertex;

public abstract class MazeBox implements Vertex {
    private final int line;
    private final int column;
    private final String name;


    public MazeBox(int x, int y) {
        line = x;
        column = y;
        name =""+x+y;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String toString(){
        return name;
    }

    public boolean isWall(){
        return false;
    }

    public boolean isEmpty(){
        return false;
    }

    public boolean isDeparture(){
        return false;
    }
    public boolean isArrival(){
        return false;
    }

}
