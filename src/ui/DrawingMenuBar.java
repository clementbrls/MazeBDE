package ui;

import javax.swing.*;

public class DrawingMenuBar extends JMenuBar {
    private FileMenu fileMenu;
    public DrawingMenuBar(DrawingApp drawingApp) {
        super();
        add(fileMenu = new FileMenu(drawingApp));

    }

}
