package ui.menu;

import ui.MazeUI;

import javax.swing.*;

public class SaveMenuItem extends JMenuItem {
    private final MazeUI drawingApp ;
    public SaveMenuItem(MazeUI drawingApp) {
        super("Sauvegarder"); // Text of menu item

        this.drawingApp = drawingApp ;
    }
}
