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
    private int width;
    private int height;
    public MazePanel(FrameUI frame,Maze maze) {
        this.frame = frame;
        this.maze=maze;
        setBackground(Color.white);
        width = (2*Hexagon.size+Hexagon.border)*maze.getWidth()+Hexagon.x_start+Hexagon.offsetOdd;
        height = (int) Math.round((Hexagon.size/(Math.cos(Math.PI/6)) + Hexagon.border + Math.tan(Math.PI/6)*Hexagon.size)*maze.getHeight()+Hexagon.y_start);
        setPreferredSize(new Dimension(width, height));
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, width, height);
        Color color=Color.lightGray;

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
        MazeBox box = Hexagon.coordToMazeBox(maze,x,y);

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
