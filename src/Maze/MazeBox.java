package Maze;

import Graph.Vertex;

public abstract class MazeBox implements Vertex {
    private final int line;
    private final int column;
    private final int label;


    public MazeBox(int x, int y) {
        line = x;
        column = y;
        label=x*10+y;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public int getLabel(){
        return label;
    }
}
