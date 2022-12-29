package ui;

import Maze.*;

import java.awt.*;

public class Hexagon {
    public static final int size = 35;
    public static final int border = 3;
    public static final int offsetOdd = size + border / 2;
    public static final int x_start = offsetOdd * 2;
    public static final int y_start = (int) Math.round(size / (Math.cos(Math.PI / 6)) + offsetOdd);


    public Hexagon() {

    }

    public static Polygon createHexa(int x, int y, int size) {
        //Fonction qui va renvoyer les coordonnées des 6 sommets d'un hexagone, en prenant le centre de celui ci en entrée
        int[] xhex = new int[6];
        int[] yhex = new int[6];
        float r = (float) (size / Math.cos(Math.PI / 6));
        for (int i = 0; i < 6; i++) {
            xhex[i] = (int) Math.round(Math.cos((Math.PI / 3) * i + Math.PI / 6) * r + x);
            yhex[i] = (int) Math.round(Math.sin((Math.PI / 3) * i + Math.PI / 6) * r + y);
        }

        return new Polygon(xhex, yhex, 6);
    }

    public static Point mazeBoxToCoord(MazeBox box) {
        int line = box.getLine();
        int column = box.getColumn();
        int x;
        int y;

        y = (int) Math.round(y_start + line * ((2 * size + border) * Math.cos(Math.PI / 6)));

        if (line % 2 == 0) x = x_start + 2 * size * column + (column * border);
        else x = (int) Math.round(x_start + 2 * size * column + (column * border) + offsetOdd);

        return new Point(x, y);
    }

    public static Polygon mazeBoxToHexa(MazeBox box) {
        Point a = mazeBoxToCoord(box);

        return createHexa(a.x, a.y, size);
    }

    public static MazeBox coordToMazeBox(Maze maze, int x, int y){
        MazeBox box = null;

        for(int i=0;i<maze.getHeight();i++){
            for(int u=0;u<maze.getWidth();u++){
                MazeBox boxTest = maze.getMazeBox(i,u);
                if(mazeBoxToCoord(boxTest).distance(new Point(x,y)) <= size){
                    box=boxTest;
                }
            }
        }
        return box;
    }
}
