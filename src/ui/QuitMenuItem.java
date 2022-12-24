package ui ;

import javax.swing.* ;

public class QuitMenuItem extends JMenuItem {

    private final DrawingApp drawingApp ;

    public QuitMenuItem(DrawingApp drawingApp) {
        super("Quit") ; // Text of menu item

        this.drawingApp = drawingApp ;
    }
}