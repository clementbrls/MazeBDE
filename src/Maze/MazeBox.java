package Maze;

import Graph.Vertex;

public abstract class MazeBox implements Vertex {
    private final int line;
    private final int column;
    private final String label;


    public MazeBox(int x, int y) {
        line = x;
        column = y;
        label=""+x+y;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getLabel(){
        return label;
    }

}
