package ui;

import maze.*;
import ui.menu.DrawingMenuBar;

import javax.swing.*;

public class FrameUI extends JFrame {
    private final DrawingMenuBar menuBar;
    private final WindowPanel windowPanel;


    private final Model model;

    public FrameUI(Maze maze) {
        super("Maze by Clément Bourles"); // Window title
        ImageIcon img = new ImageIcon("data/icon.png");//Icone de la fenetre
        setIconImage(img.getImage());

        this.model = new Model(maze);

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

    public Model getModel() {
        return model;
    }
}