package ui;

import Maze.*;

import java.awt.*;

public class GeometryFactory {
    public static final int size = 22; //width of a hexagon
    public static final int border = 3; //distance between two hexagons
    public static final int offsetOdd = size + border / 2; //offset for odd lines
    public static final int x_start = offsetOdd * 2; //x position of the first hexagon
    public static final int y_start = (int) Math.round(size / (Math.cos(Math.PI / 6)) + offsetOdd); //y position of the first hexagon


    public GeometryFactory() {

    }

    /**
     * give the center of a hexagon and it will give you the coordinates of the 6 summit of the hexagon
     * @param x the x coordinate of the center
     * @param y the y coordinate of the center
     * @param size the width of the hexagon
     * @return the coordinates of the 6 summit of the hexagon
     */
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

    /**
     * Give a mazebox, and it will give you the coordinates of the center of the hexagon
     * @param box the mazebox
     * @return the coordinates of the center of the hexagon
     */
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

    /**
     * give a mazebox and it will give you the coordinates of the 6 summit of the hexagon
     * @param box the mazebox
     * @return the coordinates of the 6 summit of the hexagon
     */
    public static Polygon mazeBoxToHexa(MazeBox box) {
        Point a = mazeBoxToCoord(box);

        return createHexa(a.x, a.y, size);
    }

    /**
     *
     * @param maze the maze
     * @param x the x coordinate of the mouse
     * @param y the y coordinate of the mouse
     * @return the mazebox where the mouse is
     */
    public static MazeBox coordToMazeBox(Maze maze, int x, int y){
        MazeBox box = null;

        for(int i=0;i<maze.getHeight();i++){
            for(int u=0;u<maze.getWidth();u++){
                MazeBox boxTest = maze.getMazeBox(i,u);
                if(mazeBoxToCoord(boxTest).distance(new Point(x,y)) <= size+border/2){// circle hitbox
                    box=boxTest;
                }
            }
        }
        return box;
    }
}
