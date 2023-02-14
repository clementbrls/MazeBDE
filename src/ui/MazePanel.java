package ui;

import Maze.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MazePanel extends JPanel implements MouseListener, MouseMotionListener, ChangeListener {
    private final FrameUI frame;
    private final int width;
    private final int height;
    private final Model model;
    boolean mouseMoved = false;

    public MazePanel(FrameUI frame) {
        this.frame = frame;
        model = frame.getModel();
        model.addObserver(this);
        setBackground(Color.white);
        width = (2 * GeometryFactory.sizeDefault + GeometryFactory.border) * model.getMaze().getWidth() + GeometryFactory.x_start + GeometryFactory.offsetOdd;
        //width=3*GeometryFactory.sizeDefault+4*GeometryFactory.border;
        height = (int) Math.round((GeometryFactory.sizeDefault / (Math.cos(Math.PI / 6)) + GeometryFactory.border + Math.tan(Math.PI / 6) * GeometryFactory.sizeDefault) * model.getMaze().getHeight() + GeometryFactory.y_start);
        setPreferredSize(new Dimension(width, height));
        addMouseListener(this);

        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, width, height);

        frame.getDrawMaze().drawMaze(g);
        if (mouseMoved) {
            frame.getDrawMaze().drawHover(g);
        }
        frame.getDrawMaze().drawPath(g);
        frame.repaintInfo();
        //System.out.println("width: "+width+" height: "+height);
        frame.pack();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        MazeBox box = GeometryFactory.coordToMazeBox(model.getMaze(), x, y);

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

        MazeBox box = GeometryFactory.coordToMazeBox(model.getMaze(), x, y);

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
        model.setBoxHover(GeometryFactory.coordToMazeBox(model.getMaze(), e.getX(), e.getY()));
        mouseMoved = true;
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
