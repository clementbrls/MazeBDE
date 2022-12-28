package ui;
import Graph.ShortestPaths;
import Maze.*;
import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    private final FrameUI frame;
    private final Maze maze;
    public MazePanel(FrameUI frame,Maze maze) {
        this.frame = frame;
        this.maze=maze;
        setBackground(Color.white);
        setPreferredSize(new Dimension(600, 500));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, 600, 500);
        Color color=Color.lightGray;

        for(int i=0;i<10;i++){
            for(int u=0;u<10;u++){
                if(maze.getMaze(i,u) instanceof WallBox) color = Color.black;
                else if(maze.getMaze(i,u) instanceof DepartureBox) color = Color.blue;
                else if(maze.getMaze(i,u) instanceof ArrivalBox) color = Color.cyan;
                else color = Color.lightGray;
                Polygon p=Hexagon.mazeBoxToHexa(maze.getMaze(i,u));
                g.setColor(color);
                g.drawPolygon(p);
                g.fillPolygon(p);
            }
        }
        ShortestPaths path = maze.dijkstra();
        for(int i=0;i<path.getPath().size();i++){
            MazeBox box =(MazeBox) path.getPath().get(i);
            MazeBox oldBox=(MazeBox) path.previous(path.getPath().get(i));
            Graphics2D g2= (Graphics2D) g;
            g.setColor(Color.red);
            g2.setStroke(new BasicStroke(5));
            g.drawLine(Hexagon.mazeBoxToCoord(box).x,Hexagon.mazeBoxToCoord(box).y,Hexagon.mazeBoxToCoord(oldBox).x,Hexagon.mazeBoxToCoord(oldBox).y);
        }


    }
}
