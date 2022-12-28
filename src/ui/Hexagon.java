package ui;

import Maze.*;

import java.awt.*;

public class Hexagon {
    private static final int size =25;
    private static final int border=2;



    public Hexagon(){

    }

    public static Polygon createHexa(int x, int y, int pr){
        //Fonction qui va renvoyer les coordonnées des 6 sommets d'un hexagone, en prenant le centre de celui ci en entrée
        int[] xhex = new int[6];
        int[] yhex = new int[6];
        float r = (float) (pr/Math.cos(Math.PI/6));
        for(int i=0;i<6;i++){
            xhex[i]= (int) Math.round(Math.cos((Math.PI/3)*i+Math.PI/6)*r+x);
            yhex[i]= (int) Math.round(Math.sin((Math.PI/3)*i+Math.PI/6)*r+y);
        }

        return new Polygon(xhex,yhex,6);
    }

    public static Point mazeBoxToCoord(MazeBox box){
        int x_start=50;
        int y_start=50;
        int line=box.getLine();
        int column=box.getColumn();
        int x;
        int y;

        y= (int) Math.round(y_start+line*((2* size +border)*Math.cos(Math.PI/6)));

        if(line%2 == 0) x=x_start+2* size *column+(column*border);
        else x= (int) Math.round(x_start+2* size *column+(column*border)+ size + border/2);

        return new Point(x,y);
    }

    public static Polygon mazeBoxToHexa(MazeBox box){
        Point a = mazeBoxToCoord(box);

        return createHexa(a.x,a.y, size);
    }


}
