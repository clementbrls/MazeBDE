package ui;

import Graph.*;
import Maze.*;


import java.awt.*;

public class DrawMaze {
    public static final int sizeDefault = 22; //width of a hexagon
    public static final int border = 3; //distance between two hexagons
    private final Model model;
    private int size = sizeDefault;
    private Graphics g;

    public DrawMaze(Model model) {
        this.model = model;
    }

    public void setInfo(Graphics g, int width, int height) {
        this.g = g;
        this.size = calcSize(model.getMaze(), width, height);
    }

    /**
     * draw the maze
     *
     * @param g Graphics
     */
    public void drawMaze() {
        Maze maze = model.getMaze();
        Color color;
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int u = 0; u < maze.getWidth(); u++) {
                MazeBox box = maze.getMazeBox(i, u);
                color = box.getColor();
                Polygon p = mazeBoxToHexa(box);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Anti-aliasing
                g.setColor(color);
                g.fillPolygon(p);
            }
        }
    }

    /**
     * draw the path
     *
     * @param g Graphics
     */
    public void drawPath() {
        VertexPath path = model.getMaze().getPath();

        if (model.getAutoDijkstra() && path.getDistance() == -1) {
            path = model.getMaze().dijkstra();
        }

        for (int i = 0; i < path.size(); i++) {
            if (i + 1 < path.size()) {
                MazeBox box = (MazeBox) path.get(i);
                MazeBox oldBox = (MazeBox) path.get(i + 1);
                Graphics2D g2 = (Graphics2D) g;
                g.setColor(new Color(0, 168, 224));
                g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Line more thick with rounded corners
                g.drawLine(mazeBoxToCoord(box).x, mazeBoxToCoord(box).y, mazeBoxToCoord(oldBox).x, mazeBoxToCoord(oldBox).y);
            }
        }
    }

    public void drawHover() {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Anti-aliasing

        MazeBox boxHover = model.getBoxHover();
        if (boxHover != null) {
            g2.setColor(boxHover.getColor().darker());
            Polygon p = mazeBoxToHexa(boxHover);
            g2.fillPolygon(p);

        }
    }

    //------------------------------------GeometryFactory------------------------------------//
    int offsetOdd = size + border / 2;
    int x_start = offsetOdd * 2; //x position of the first hexagon
    int y_start = size * 2; //y position of the first hexagon

    public int calcSize(Maze maze, int width, int height) {
        int sizeWidth = (width - border * maze.getWidth()) / (2 + 2 * maze.getWidth());
        int sizeHeight = (int) Math.round((height - (maze.getHeight() + 1) * Math.cos(Math.PI / 6) * border) / ((maze.getHeight() + 1) * 2 * Math.cos(Math.PI / 6) + 1));


        //width= (int) DrawMaze.GeometryFactory.x_start+(2*DrawMaze.GeometryFactory.sizeDefault+DrawMaze.GeometryFactory.border)*model.getMaze().getWidth()+DrawMaze.GeometryFactory.x_start/2;
        //height= (int) Math.round(DrawMaze.GeometryFactory.sizeDefault + (model.getMaze().getHeight()) * ((2 * DrawMaze.GeometryFactory.sizeDefault + DrawMaze.GeometryFactory.border * Math.cos(Math.PI / 6))));

        if(sizeHeight > sizeWidth) {
            offsetOdd = size + border / 2;
            x_start = size*2; //x position of the first hexagon
            int sizetheo=(int) Math.round(sizeWidth + (maze.getHeight()) * ((2 * sizeWidth + border * Math.cos(Math.PI / 6))));
            y_start = (height/2)-(sizetheo/2)+sizeWidth*2; //y position of the first hexagon
            return sizeWidth;
        } else {
            offsetOdd = size + border / 2;

            y_start = size * 2; //y position of the first hexagon
            int sizetheo=(int) sizeHeight*2+(2*sizeHeight+border)*maze.getWidth()+sizeHeight;
            x_start = (width/2)-(sizetheo/2)+sizeHeight*2 ; //x position of the first hexagon
            return sizeHeight;
        }
    }


    /**
     * give the center of a hexagon and it will give you the coordinates of the 6 summit of the hexagon
     *
     * @param x    the x coordinate of the center
     * @param y    the y coordinate of the center
     * @param size the width of the hexagon
     * @return the coordinates of the 6 summit of the hexagon
     */
    public Polygon createHexa(int x, int y) {
        //Fonction qui va renvoyer les coordonnées des 6 sommets d'un hexagone, en prenant le centre de celui ci en entrée
        int[] xhex = new int[6];
        int[] yhex = new int[6];
        float arc = (float) (size / Math.cos(Math.PI / 6));
        for (int i = 0; i < 6; i++) {
            xhex[i] = (int) Math.round(Math.cos((Math.PI / 3) * i + Math.PI / 6) * arc + x);
            yhex[i] = (int) Math.round(Math.sin((Math.PI / 3) * i + Math.PI / 6) * arc + y);
        }
        return new Polygon(xhex, yhex, 6);
    }


    /**
     * Give a mazebox, and it will give you the coordinates of the center of the hexagon
     *
     * @param box the mazebox
     * @return the coordinates of the center of the hexagon
     */
    public Point mazeBoxToCoord(MazeBox box) {
        int line = box.getLine();
        int column = box.getColumn();
        int x;
        int y;
        //int offsetOdd = size + border / 2; //offset for odd lines
        /*
        int x_start = offsetOdd * 2; //x position of the first hexagon
        int y_start = size * 2; //y position of the first hexagon

         */

        y = (int) Math.round(y_start + line * ((2 * size + border) * Math.cos(Math.PI / 6)));

        if (line % 2 == 0) x = x_start + 2 * size * column + (column * border);
        else x = Math.round(x_start + 2 * size * column + (column * border) + offsetOdd);


        return new Point(x, y);
    }


    /**
     * give a mazebox and it will give you the coordinates of the 6 summit of the hexagon
     *
     * @param box the mazebox
     * @return the coordinates of the 6 summit of the hexagon
     */
    public Polygon mazeBoxToHexa(MazeBox box) {
        Point a = mazeBoxToCoord(box);

        return createHexa(a.x, a.y);
    }

    /**
     * @param maze the maze
     * @param x    the x coordinate of the mouse
     * @param y    the y coordinate of the mouse
     * @return the mazebox where the mouse is
     */
    public MazeBox coordToMazeBox(Maze maze, int x, int y, int size) {
        MazeBox box = null;

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int u = 0; u < maze.getWidth(); u++) {
                MazeBox boxTest = maze.getMazeBox(i, u);
                if (mazeBoxToHexa(boxTest).contains(x, y)) {
                    box = boxTest;
                }
            }
        }
        return box;
    }
}
