package ui;

import Maze.*;
import ui.menu.DrawingMenuBar;

import javax.swing.*;

public class FrameUI extends JFrame {
    private final DrawingMenuBar menuBar;
    private final WindowPanel windowPanel;


    private final DrawMaze drawMaze;

    public FrameUI(Maze maze) {
        super("Labyrinthe"); // Window title
        ImageIcon img = new ImageIcon("data/icon.png");
        setIconImage(img.getImage());

        this.drawMaze = new DrawMaze(this, maze);

        //Affichage de la barre de menu
        menuBar = new DrawingMenuBar(this, maze);
        setJMenuBar(menuBar);
        //Affichage du panneau principal (qui contient les autres panneaux)
        setContentPane(windowPanel = new WindowPanel(this));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Explicit !

        pack();// Components sizes and positions
        setLocationRelativeTo(null);//Center frame on the screen
        setVisible(true);  // The great show
    }

    public DrawMaze getdMaze() {
        return drawMaze;
    }


}