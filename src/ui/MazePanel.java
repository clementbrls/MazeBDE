package ui;
import Graph.*;
import Maze.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MazePanel extends JPanel implements MouseListener {
    private final FrameUI frame;
    private final Maze maze;
    private final int width;
    private final int height;
    public MazePanel(FrameUI frame,Maze maze) {
        this.frame = frame;
        this.maze=maze;
        setBackground(Color.white);
        width = (2* Geometry.size+ Geometry.border)*maze.getWidth()+ Geometry.x_start+ Geometry.offsetOdd;
        height = (int) Math.round((Geometry.size/(Math.cos(Math.PI/6)) + Geometry.border + Math.tan(Math.PI/6)* Geometry.size)*maze.getHeight()+ Geometry.y_start);
        setPreferredSize(new Dimension(width, height));
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, width, height);

        DrawMaze.drawMaze(g,maze);

        VertexPath path = maze.dijkstra();
        DrawMaze.drawPath(g,path);



    }

    @Override
    public void mouseClicked(MouseEvent e){
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        MazeBox box = Geometry.coordToMazeBox(maze,x,y);

        if(box!=null){

            DrawMaze.changeBox(maze,box);
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
            }

    @Override
    public void mouseExited(MouseEvent e) {

    }



}
