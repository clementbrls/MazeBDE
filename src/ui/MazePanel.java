package ui;

import Maze.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;


//----- Controleur -----
public class MazePanel extends JPanel implements MouseListener, MouseMotionListener, ChangeListener {
    private final Model model;
    private final DrawMaze drawMaze;
    boolean mouseMoved = false;
    private final FrameUI frame;

    /**
     * Constructor of the MazePanel
     * @param frame the frame
     */
    public MazePanel(FrameUI frame) {
        setPreferredSize(new Dimension(550, 450));
        this.frame=frame;
        model = frame.getModel();
        this.drawMaze = new DrawMaze(frame.getModel());

        model.addObserver(this);//Ajoute le pannel comme observer du model
        addMouseListener(this);//Ajoute le pannel comme listener de la souris
        addMouseMotionListener(this);

        setBackground(Color.white);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawMaze.setInfo(g,getWidth(),getHeight());//Donne les infos nécessaires pour dessiner le labyrinthe
        drawMaze.drawMaze();//Dessine le labyrinthe
        if (mouseMoved) {
            drawMaze.drawHover();//Dessine la case survolée par la souris
        }
        drawMaze.drawPath();//Dessine le chemin trouvé par Dijkstra
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    //Changer la case quand on clique dessus
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
        mouseMoved = false;//Pour eviter que la case survolé reste affiché en "hover"

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

    //Permet de changer les cases en les restant appuyé
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


    //Permet de changer la case survolé par la souris
    @Override
    public void mouseMoved(MouseEvent e) {
        model.setBoxHover(drawMaze.coordToMazeBox(model.getMaze(), e.getX(), e.getY()));
        mouseMoved = true;
    }

    //Méthode appellé par l'observable qui permet d'afficher le nouveau labyrinthe quand celui-ci change
    @Override
    public void stateChanged(ChangeEvent e) {
        frame.revalidate();
        frame.repaint();//Permet de redessiner le labyrinthe, on est obligé de faire un frame.repaint et pas juste un repaint car sinon ça provoque du lag, le temps que la frame se repaint
    }
}
