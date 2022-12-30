package ui;
import Graph.*;
import Maze.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MazePanel extends JPanel implements MouseListener {
    private final FrameUI frame;
    private final int width;
    private final int height;
    public MazePanel(FrameUI frame) {
        this.frame = frame;
        DrawMaze drawMaze=frame.getDrawMaze();
        setBackground(Color.white);
        width = (2* Geometry.size+ Geometry.border)*drawMaze.getMaze().getWidth()+ Geometry.x_start+ Geometry.offsetOdd;
        height = (int) Math.round((Geometry.size/(Math.cos(Math.PI/6)) + Geometry.border + Math.tan(Math.PI/6)* Geometry.size)*drawMaze.getMaze().getHeight()+ Geometry.y_start);
        setPreferredSize(new Dimension(width, height));
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.clearRect(0, 0, width, height);

        frame.getDrawMaze().drawMaze(g);

        if(frame.getDrawMaze().getAutoDijkstra()) {
            frame.getDrawMaze().drawPath(g);
        }


    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();

        MazeBox box = Geometry.coordToMazeBox(frame.getDrawMaze().getMaze(),x,y);

        if(box!=null){
            if(SwingUtilities.isLeftMouseButton(e)) {
                frame.getDrawMaze().changeBox(box);
            } else if (SwingUtilities.isRightMouseButton(e)){
                frame.getDrawMaze().changeBox(box,true);
            }
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        frame.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
            }

    @Override
    public void mouseExited(MouseEvent e) {

    }



}
