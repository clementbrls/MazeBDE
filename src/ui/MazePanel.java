package ui;

import Maze.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;


//----- Controleur -----
public class MazePanel extends JPanel implements MouseListener, MouseMotionListener, ChangeListener {
    private final FrameUI frame;//la frame
    private final Model model;
    private final DrawMaze drawMaze;
    boolean mouseMoved = false;

    /**
     * Constructor of the MazePanel
     * @param frame the frame
     */
    public MazePanel(FrameUI frame) {
        setPreferredSize(new Dimension(550, 450));
        this.frame = frame;
        model = frame.getModel();
        this.drawMaze = new DrawMaze(frame.getModel());

        model.addObserver(this);//Ajoute le pannel comme observer du model
        addMouseListener(this);//Ajoute le pannel comme listener de la souris
        addMouseMotionListener(this);



        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMaze.setInfo(g,getWidth(),getHeight());//Donne les infos neccessaire pour dessiner le labyrinthe
        drawMaze.drawMaze();//Dessine le labyrinthe
        if (mouseMoved) {
            drawMaze.drawHover();//Dessine la case survolé par la souris
        }
        drawMaze.drawPath();//Dessine le chemin trouvé par Dijkstra

        //frame.repaintInfo();//Casse le principe d'O.O, a channger
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        MazeBox box = drawMaze.coordToMazeBox(model.getMaze(), x, y);//Récupère la case sur laquelle on a cliqué

        if (box != null) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                model.changeBox(box);//change la case
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

        MazeBox box = drawMaze.coordToMazeBox(model.getMaze(), x, y);

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
        model.setBoxHover(drawMaze.coordToMazeBox(model.getMaze(), e.getX(), e.getY()));
        mouseMoved = true;
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
