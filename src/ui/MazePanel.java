package ui;

import Maze.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class MazePanel extends JPanel implements MouseListener, MouseMotionListener, ChangeListener {
    private final FrameUI frame;
    private final Model model;
    private final DrawMaze drawMaze;
    private int size;
    boolean mouseMoved = false;

    public MazePanel(FrameUI frame) {
        setPreferredSize(new Dimension(550, 450));
        this.frame = frame;
        model = frame.getModel();
        this.size=20;

        model.addObserver(this);
        this.drawMaze = new DrawMaze(frame.getModel());
        setBackground(Color.white);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.clearRect(0, 0, width, height);
        size = drawMaze.calcSize(model.getMaze(), getWidth(), getHeight());
        drawMaze.setInfo(g,getWidth(),getHeight());
        drawMaze.drawMaze();
        if (mouseMoved) {
            drawMaze.drawHover();
        }
        drawMaze.drawPath();
        frame.repaintInfo();
        //System.out.println("width: "+width+" height: "+height);
        System.out.println("width: " + getWidth() + " height: " + getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        MazeBox box = drawMaze.coordToMazeBox(model.getMaze(), x, y,size);

        if (box != null) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                model.changeBox(box);
            } else if (SwingUtilities.isRightMouseButton(e)) {
                model.changeBox(box, true);//permet d'effacer une case avec un clic droit
            }

        }
        mouseMoved = false;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseMoved = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        MazeBox box = drawMaze.coordToMazeBox(model.getMaze(), x, y,size);

        if (box != null) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                model.changeBox(box);
            } else if (SwingUtilities.isRightMouseButton(e)) {
                model.changeBox(box, true);//permet d'effacer une case avec un clic droit
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        model.setBoxHover(drawMaze.coordToMazeBox(model.getMaze(), e.getX(), e.getY(),size));
        mouseMoved = true;
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
