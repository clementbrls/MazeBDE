package ui.menu;

import ui.MazeUI;

import javax.swing.*;

public class OpenMenuItem extends JMenuItem {

    private final MazeUI drawingApp ;

    public OpenMenuItem(MazeUI drawingApp) {
        super("Ouvrir") ; // Text of menu item

        this.drawingApp = drawingApp ;
    }
}
