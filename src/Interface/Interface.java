package Interface;

import javax.swing.* ;

public class Interface extends JFrame {

    public Interface() {
        super("Labyrinthe") ; // Window title



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; // Explicit !

        pack() ;            // Components sizes and positions
        setVisible(true) ;  // The great show
    }

}