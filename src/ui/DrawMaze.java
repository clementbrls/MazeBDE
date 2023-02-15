package ui;

import Graph.*;
import Maze.*;


import java.awt.*;
import java.awt.geom.*;

public class DrawMaze {
    public static final float sizeDefault = 22; //width of a hexagon
    public float border = 3; //distance between two hexagons
    private final Model model;
    private float size = sizeDefault;
    private Graphics2D g2;

    public DrawMaze(Model model) {
        this.model = model;
    }

    public void setInfo(Graphics g, int width, int height) {
        this.g2 =(Graphics2D) g;
        this.size = calcSize(model.getMaze(), (float)width, (float)height);
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
                Path2D hexa = mazeBoxToHexa(box);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Anti-aliasing
                g2.setColor(color);
                g2.fill(hexa);
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
                g2.setColor(new Color(0, 168, 224));
                float stroke= border*2;
                g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Line more thick with rounded corners
                Point2D pBox = mazeBoxToCoord(box);
                Point2D pOldBox = mazeBoxToCoord(oldBox);
                g2.draw(new Line2D.Double(pBox, pOldBox));
            }
        }
    }

    public void drawHover() {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Anti-aliasing

        MazeBox boxHover = model.getBoxHover();
        if (boxHover != null) {
            g2.setColor(boxHover.getColor().darker());
            Path2D hexa = mazeBoxToHexa(boxHover);
            g2.fill(hexa);

        }
    }

    //------------------------------------GeometryFactory------------------------------------//
    float offsetOdd = size + border / 2;
    float x_start = offsetOdd * 2; //x position of the first hexagon
    float y_start = size * 2; //y position of the first hexagon

    public float calcSize(Maze maze, float width, float height) {
        float sizeWidth = (width - border * maze.getWidth()) / (2 + 2 * maze.getWidth()+1);
        float sizeHeight = (float) ((height - (maze.getHeight() + 1) * Math.cos(Math.PI / 6) * border) / ((maze.getHeight() + 1) * 2 * Math.cos(Math.PI / 6) + 1));

        border= (float) Math.min(sizeWidth, sizeHeight)*0.15f;


        if(sizeHeight > sizeWidth) {
            offsetOdd = size + border / 2;
            x_start = size*2; //x position of the first hexagon
            float sizetheo= (float) (sizeWidth + (maze.getHeight()) * ((2 * sizeWidth + border * Math.cos(Math.PI / 6))));
            y_start = (height/2)-(sizetheo/2)+sizeWidth*2.5f; //y position of the first hexagon
            return sizeWidth;
        } else {
            offsetOdd = size + border / 2;
            y_start = size * 2; //y position of the first hexagon
            float sizetheo= sizeHeight*2+(2*sizeHeight+border)*maze.getWidth()+sizeHeight;
            x_start = (width/2)-(sizetheo/2)+sizeHeight*2; //x position of the first hexagon
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
    public Path2D createHexa(float x, float y) {
        //Fonction qui va renvoyer les coordonnées des 6 sommets d'un hexagone, en prenant le centre de celui ci en entrée
        float arc = (float) (size / Math.cos(Math.PI / 6));
        Path2D hexa= new Path2D.Float();
        hexa.moveTo(x, y);
        for (int i = 0; i <= 6; i++) {
            float nextX = (float) (Math.cos((Math.PI / 3) * i + Math.PI / 6) * arc + x);
            float nextY = (float) (Math.sin((Math.PI / 3) * i + Math.PI / 6) * arc + y);
            hexa.lineTo(nextX, nextY);
        }
        hexa.closePath();
        return hexa;
    }


    /**
     * Give a mazebox, and it will give you the coordinates of the center of the hexagon
     *
     * @param box the mazebox
     * @return the coordinates of the center of the hexagon
     */
    public Point2D mazeBoxToCoord(MazeBox box) {
        int line = box.getLine();
        int column = box.getColumn();
        float x;
        float y;

        y = (float) Math.round(y_start + line * ((2 * size + border) * Math.cos(Math.PI / 6)));

        if (line % 2 == 0) x = x_start + 2 * size * column + (column * border);
        else x = x_start + 2 * size * column + (column * border) + offsetOdd;


        return new Point2D.Float(x, y);
    }


    /**
     * give a mazebox and it will give you the coordinates of the 6 summit of the hexagon
     *
     * @param box the mazebox
     * @return the coordinates of the 6 summit of the hexagon
     */
    public Path2D mazeBoxToHexa(MazeBox box) {
        Point2D a = mazeBoxToCoord(box);

        return createHexa((float)a.getX(), (float)a.getY());
    }

    /**
     * @param maze the maze
     * @param x    the x coordinate of the mouse
     * @param y    the y coordinate of the mouse
     * @return the mazebox where the mouse is
     */
    public MazeBox coordToMazeBox(Maze maze, int x, int y) {
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
