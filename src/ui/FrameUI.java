package ui;

import ui.menu.DrawingMenuBar;

import javax.swing.* ;

public class FrameUI extends JFrame {
    private final DrawingMenuBar menuBar;
    private final WindowPanel windowPanel;
    public FrameUI() {
        super("Labyrinthe") ; // Window title

        //Affichage de la barre de menu
        menuBar = new DrawingMenuBar(this);
        setJMenuBar(menuBar);
        //Affichage du paneau principal (qui contient les autres panneaux)
        setContentPane(windowPanel = new WindowPanel(this));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; // Explicit !

        pack() ;            // Components sizes and positions
        setVisible(true) ;  // The great show
    }

}