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
        width = (2*Hexagon.size+Hexagon.border)*maze.getWidth()+Hexagon.x_start+Hexagon.offsetOdd;
        height = (int) Math.round((Hexagon.size/(Math.cos(Math.PI/6)) + Hexagon.border + Math.tan(Math.PI/6)*Hexagon.size)*maze.getHeight()+Hexagon.y_start);
        setPreferredSize(new Dimension(width, height));
        System.out.println("width : "+width+" Height : "+height);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, width, height);
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

    @Override
    public void mouseClicked(MouseEvent e){
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        System.out.println("Box click : "+Hexagon.coordToMazeBox(maze,x,y).getLabel());
        MazeBox box = Hexagon.coordToMazeBox(maze,x,y);

        if(box!=null){

            maze.setBox(new WallBox(box.getLine(),box.getColumn()));
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
