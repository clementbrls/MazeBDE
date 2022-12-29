package ui;
import Graph.*;
import Maze.*;

import java.awt.*;


public class DrawMaze {

    private static String select="E";
    public static final Color colorArrival= new Color(254, 147, 140);
    public static final Color colorDeparture = new Color(71, 229, 188);
    public static final Color colorWall = new Color(54, 65, 86);
    public static final Color colorEmpty = new Color(236, 235, 243);
    public static final Color colorDefault = new Color(216, 229, 235);

    public DrawMaze(){
    }


    public static void drawMaze(Graphics g, Maze maze){
        Color color=Color.lightGray;
        for(int i=0;i<maze.getHeight();i++){
            for(int u=0;u<maze.getWidth();u++){
                if(maze.getMazeBox(i,u).isWall()) color = colorWall;
                else if(maze.getMazeBox(i,u).isDeparture()) color = colorDeparture;
                else if(maze.getMazeBox(i,u).isArrival()) color = colorArrival;
                else color = colorEmpty;
                Polygon p= Geometry.mazeBoxToHexa(maze.getMazeBox(i,u));
                g.setColor(color);
                g.drawPolygon(p);
                g.fillPolygon(p);
            }
        }
    }

    public static void drawPath(Graphics g, VertexPath path){
        if(path!=null) {
            for (int i = 0; i < path.size(); i++) {
                if (i + 1 < path.size()) {
                    MazeBox box = (MazeBox) path.get(i);
                    MazeBox oldBox = (MazeBox) path.get(i + 1);
                    Graphics2D g2 = (Graphics2D) g;
                    g.setColor(new Color(7, 160, 195));
                    g2.setStroke(new BasicStroke(5));
                    g.drawLine(Geometry.mazeBoxToCoord(box).x, Geometry.mazeBoxToCoord(box).y, Geometry.mazeBoxToCoord(oldBox).x, Geometry.mazeBoxToCoord(oldBox).y);
                }
            }
        }
    }

    public static String getSelect(){
        return select;
    }

    public static void setSelect(String selection){
        select=selection;
    }

    public static void changeBox(Maze maze,MazeBox box){
       maze.changeBox(box,select);
    }

}
