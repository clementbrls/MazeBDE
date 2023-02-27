package ui;

import maze.MazeBox;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


//----- Controleur -----
public class MazePanel extends JPanel implements MouseListener, MouseMotionListener, ChangeListener {
    private final Model model;
    private final DrawMaze drawMaze;
    boolean mouseMoved = false;
    private final FrameUI frame;

    /**
     * Constructor of the MazePanel
     *
     * @param frame the frame
     */
    public MazePanel(FrameUI frame) {
        this.frame = frame;
        model = frame.getModel();
        this.drawMaze = new DrawMaze(frame.getModel());

        int maxWidthSize = (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.8 - 120 - 50);
        int maxHeightSize = (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.8);
        float ratio = (model.getMaze().getWidth() / (float) model.getMaze().getHeight());
        Dimension dim;
        if (ratio > 1) {
            dim = new Dimension(maxWidthSize, (int) Math.round(maxWidthSize / ratio));
        } else {
            dim = new Dimension((int) Math.round(maxHeightSize * ratio), maxHeightSize);
        }
        setPreferredSize(dim);


        model.addObserver(this);//Ajoute le panel comme observer du model
        addMouseListener(this);//Ajoute le panel comme listener de la souris
        addMouseMotionListener(this);

        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMaze.setInfo(g, getWidth(), getHeight());//Donne les infos nécessaires pour dessiner le labyrinthe
        drawMaze.drawMaze();//Dessine le labyrinthe
        if (mouseMoved) {
            drawMaze.drawHover();//Dessine la case survolée par la souris
        }
        drawMaze.drawPath();//Dessine le chemin trouvé par Dijkstra
        g.dispose();
        frame.revalidate();
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
        mouseMoved = false;//Pour éviter que la case survolée reste affiché en "hover" quand on clique
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


    //Permet de changer la case survolée par la souris
    @Override
    public void mouseMoved(MouseEvent e) {
        model.setBoxHover(drawMaze.coordToMazeBox(model.getMaze(), e.getX(), e.getY()));
        mouseMoved = true;
    }

    //Méthode appellé par l'observable qui permet d'afficher le nouveau labyrinthe quand celui-ci change
    @Override
    public void stateChanged(ChangeEvent e) {
        frame.revalidate();
        frame.repaint();//Permet de redessiner le labyrinthe, on est obligé de faire un frame.repaint et pas juste un repaint car sinon ça provoque un lag sur un seul PC (même si le PC est pourtant rapide, c'est bizarre)

    }

}
