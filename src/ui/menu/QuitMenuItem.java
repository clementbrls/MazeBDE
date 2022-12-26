package ui.menu;

import ui.MazeUI;

import javax.swing.* ;

public class QuitMenuItem extends JMenuItem {

    private final MazeUI drawingApp ;

    public QuitMenuItem(MazeUI drawingApp) {
        super("Quit") ; // Text of menu item

        this.drawingApp = drawingApp ;
    }
}