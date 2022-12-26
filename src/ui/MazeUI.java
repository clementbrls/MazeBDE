package ui;

import ui.menu.DrawingMenuBar;

import javax.swing.* ;

public class MazeUI extends JFrame {
    private final DrawingMenuBar menuBar;
    public MazeUI() {
        super("Labyrinthe") ; // Window title

        menuBar = new DrawingMenuBar(this);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; // Explicit !

        pack() ;            // Components sizes and positions
        setVisible(true) ;  // The great show
    }

}