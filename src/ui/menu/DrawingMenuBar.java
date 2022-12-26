package ui.menu;

import ui.MazeUI;

import javax.swing.*;

public class DrawingMenuBar extends JMenuBar {
    private FileMenu fileMenu;
    public DrawingMenuBar(MazeUI drawingApp) {
        super();
        add(fileMenu = new FileMenu(drawingApp));

    }

}
