package ui;

import javax.swing.*;

public class OpenMenuItem extends JMenuItem {

    private final DrawingApp drawingApp ;

    public OpenMenuItem(DrawingApp drawingApp) {
        super("Ouvrir") ; // Text of menu item

        this.drawingApp = drawingApp ;
    }
}
