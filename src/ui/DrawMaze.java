package ui;
import Graph.*;
import Maze.*;

import java.awt.*;


public class DrawMaze {

    private static String select="E";

    public DrawMaze(){
    }


    public static void drawMaze(Graphics g, Maze maze){
        Color color=Color.lightGray;
        for(int i=0;i<maze.getHeight();i++){
            for(int u=0;u<maze.getWidth();u++){
                if(maze.getMazeBox(i,u).isWall()) color = Color.black;
                else if(maze.getMazeBox(i,u).isDeparture()) color = Color.blue;
                else if(maze.getMazeBox(i,u).isArrival()) color = Color.cyan;
                else color = Color.lightGray;
                Polygon p=Hexagon.mazeBoxToHexa(maze.getMazeBox(i,u));
                g.setColor(color);
                g.drawPolygon(p);
                g.fillPolygon(p);
            }
        }
    }

    public static void drawPath(Graphics g, VertexPath path){
        for(int i=0;i<path.size();i++){
            if(i+1<path.size()) {
                MazeBox box = (MazeBox) path.get(i);
                MazeBox oldBox = (MazeBox) path.get(i + 1);
                Graphics2D g2 = (Graphics2D) g;
                g.setColor(Color.red);
                g2.setStroke(new BasicStroke(5));
                g.drawLine(Hexagon.mazeBoxToCoord(box).x, Hexagon.mazeBoxToCoord(box).y, Hexagon.mazeBoxToCoord(oldBox).x, Hexagon.mazeBoxToCoord(oldBox).y);
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
       switch(select){
           case "A":
               maze.setBox(new ArrivalBox(box.getLine(),box.getColumn()));
               break;
           case "D":
               maze.setBox(new DepartureBox(box.getLine(),box.getColumn()));
               break;
           case "E":
               maze.setBox(new EmptyBox(box.getLine(),box.getColumn()));
               break;
           case "W":
               maze.setBox(new WallBox(box.getLine(),box.getColumn()));
           break;
       }


    }

}
