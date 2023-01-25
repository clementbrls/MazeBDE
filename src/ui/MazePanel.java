package ui;
import Maze.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MazePanel extends JPanel implements MouseListener,MouseMotionListener {
    private final FrameUI frame;
    boolean mouseMoved = false;
    private final int width;
    private final int height;
    public MazePanel(FrameUI frame) {
        this.frame = frame;
        Model model=frame.getModel();
        setBackground(Color.white);
        width = (2* GeometryFactory.sizeDefault + GeometryFactory.border)*model.getMaze().getWidth()+ GeometryFactory.x_start+ GeometryFactory.offsetOdd;
        height = (int) Math.round((GeometryFactory.sizeDefault /(Math.cos(Math.PI/6)) + GeometryFactory.border + Math.tan(Math.PI/6)* GeometryFactory.sizeDefault)*model.getMaze().getHeight()+ GeometryFactory.y_start);
        setPreferredSize(new Dimension(width, height));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, width, height);

        frame.getDrawMaze().drawMaze(g);
        if(mouseMoved){
            frame.getDrawMaze().drawHover(g);
        }
        frame.getDrawMaze().drawPath(g);
        frame.repaintInfo();

        //System.out.println("width: "+width+" height: "+height);
    }

    @Override
    public void mouseClicked(MouseEvent e){
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        MazeBox box = GeometryFactory.coordToMazeBox(frame.getModel().getMaze(),x,y);

        if(box!=null){
            if(SwingUtilities.isLeftMouseButton(e)) {
                frame.getModel().changeBox(box);
            } else if (SwingUtilities.isRightMouseButton(e)){
                frame.getModel().changeBox(box,true);//permet d'effacer une case avec un clic droit
            }

        }
        mouseMoved=false;
        repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseMoved=false;
        //frame.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
            }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        int x=e.getX();
        int y=e.getY();

        MazeBox box = GeometryFactory.coordToMazeBox(frame.getModel().getMaze(),x,y);

        if(box!=null){
            if(SwingUtilities.isLeftMouseButton(e)) {
                frame.getModel().changeBox(box);
            } else if (SwingUtilities.isRightMouseButton(e)){
                frame.getModel().changeBox(box,true);//permet d'effacer une case avec un clic droit
            }

        }

        if(frame.getModel().isMazeChanged()) {
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if(frame.getModel().setBoxHover(GeometryFactory.coordToMazeBox(frame.getModel().getMaze(),e.getX(),e.getY()))) {
            repaint();
        }

        mouseMoved=true;

    }


}
