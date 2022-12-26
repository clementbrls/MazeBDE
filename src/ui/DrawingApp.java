package ui;

import javax.swing.* ;
import java.awt.*;

public class DrawingApp extends JFrame {
    private final DrawingMenuBar menuBar;
    public DrawingApp() {
        super("Labyrinthe") ; // Window title

        menuBar = new DrawingMenuBar(this);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; // Explicit !

        pack() ;            // Components sizes and positions
        setVisible(true) ;  // The great show
    }

}