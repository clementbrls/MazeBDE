package ui;
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
        DrawMaze drawMaze=frame.getdMaze();
        setBackground(Color.white);
        width = (2* GeometryFactory.size+ GeometryFactory.border)*drawMaze.getMaze().getWidth()+ GeometryFactory.x_start+ GeometryFactory.offsetOdd;
        height = (int) Math.round((GeometryFactory.size/(Math.cos(Math.PI/6)) + GeometryFactory.border + Math.tan(Math.PI/6)* GeometryFactory.size)*drawMaze.getMaze().getHeight()+ GeometryFactory.y_start);
        setPreferredSize(new Dimension(width, height));
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, width, height);

        frame.getdMaze().drawMaze(g);
        frame.getdMaze().drawPath(g);
    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();

        MazeBox box = GeometryFactory.coordToMazeBox(frame.getdMaze().getMaze(),x,y);

        if(box!=null){
            if(SwingUtilities.isLeftMouseButton(e)) {
                frame.getdMaze().changeBox(box);
            } else if (SwingUtilities.isRightMouseButton(e)){
                frame.getdMaze().changeBox(box,true);//permet d'effacer une case avec un clic droit
            }

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
