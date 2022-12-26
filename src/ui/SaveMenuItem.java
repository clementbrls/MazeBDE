package ui;

import javax.swing.*;

public class SaveMenuItem extends JMenuItem {
    private final DrawingApp drawingApp ;
    public SaveMenuItem(DrawingApp drawingApp) {
        super("Sauvegarder"); // Text of menu item

        this.drawingApp = drawingApp ;
    }
}
