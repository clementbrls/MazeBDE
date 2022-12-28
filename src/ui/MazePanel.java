package ui;
import Graph.*;
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
        Path path = maze.dijkstra();
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
}
