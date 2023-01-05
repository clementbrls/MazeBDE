package ui;
import Graph.*;
import Maze.*;

import java.awt.*;


public class DrawMaze {

    public static final Color colorArrival = new Color(254, 147, 140);
    public static final Color colorDeparture = new Color(71, 229, 188);
    public static final Color colorWall = new Color(54, 65, 86);
    public static final Color colorEmpty = new Color(236, 235, 243);
    public static final Color colorDefault = new Color(241, 243, 244);
    private final Maze maze;
    private char select = WallBox.Label;
    private Boolean autoDijkstra = true;
    private FrameUI frame;


    public DrawMaze(FrameUI frame,Maze maze) {
        this.frame=frame;
        this.maze = maze;
    }

    /**
     * draw the maze
     * @param g Graphics
     */
    public void drawMaze(Graphics g) {
        Color color;
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int u = 0; u < maze.getWidth(); u++) {
                if (maze.getMazeBox(i, u).isWall()) color = colorWall;
                else if (maze.getMazeBox(i, u).isDeparture()) color = colorDeparture;
                else if (maze.getMazeBox(i, u).isArrival()) color = colorArrival;
                else color = colorEmpty;
                Polygon p = GeometryFactory.mazeBoxToHexa(maze.getMazeBox(i, u));
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Anti-aliasing
                g.setColor(color);
               // g.drawPolygon(p);
                g.fillPolygon(p);

            }
        }
    }

    public void drawHover(Graphics g,int x, int y){
        MazeBox boxHover = GeometryFactory.coordToMazeBox(maze,x,y);
        g.fillPolygon(GeometryFactory.mazeBoxToHexa(boxHover));
    }

    /**
     * draw the path
     * @param g Graphics
     */
    public void drawPath(Graphics g) {
        VertexPath path = maze.getPath();
        if (autoDijkstra && path.getDistance()==-1) {
            path = maze.dijkstra();
        }


        for (int i = 0; i < path.size(); i++) {
            if (i + 1 < path.size()) {
                MazeBox box = (MazeBox) path.get(i);
                MazeBox oldBox = (MazeBox) path.get(i + 1);
                Graphics2D g2 = (Graphics2D) g;
                g.setColor(new Color(0, 168, 224));
                g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Line more thick with rounded corners
                g.drawLine(GeometryFactory.mazeBoxToCoord(box).x, GeometryFactory.mazeBoxToCoord(box).y, GeometryFactory.mazeBoxToCoord(oldBox).x, GeometryFactory.mazeBoxToCoord(oldBox).y);
            }
        }

        frame.repaint();
    }

    /**
     * get the type of element to draw
     * @return
     */
    public char getSelect() {
        return select;
    }

    /**
     * set the type of element to draw
     * @param selection
     */
    public void setSelect(char selection) {
        select = selection;
    }

    /**
     * change the box clicked
     * @param box the box to change
     */
    public void changeBox(MazeBox box) {
        maze.changeBox(box, select);
    }

    public void changeBox(MazeBox box, Boolean setEmpty) {
        if (setEmpty) {
            maze.changeBox(box, EmptyBox.Label);
        } else {
            maze.changeBox(box, select);
        }
    }

    /**
     * get the distance to solve the maze
     * @return the distance
     */
    public int getDistance() {
        return maze.getPath().getDistance();
    }

    /**
     * get the maze
     * @return the maze
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * get the autoDijkstra parameter
     * @return the autoDijkstra parameter
     */
    public Boolean getAutoDijkstra() {
        return autoDijkstra;
    }

    /**
     * set the autoDijkstra parameter
     * @param autoDijkstra can be true or false
     */
    public void setAutoDijkstra(Boolean autoDijkstra) {
        this.autoDijkstra = autoDijkstra;
    }
}
