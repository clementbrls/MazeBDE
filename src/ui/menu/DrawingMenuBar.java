package ui.menu;

import ui.FrameUI;

import javax.swing.*;

public class DrawingMenuBar extends JMenuBar {
    private FileMenu fileMenu;
    public DrawingMenuBar(FrameUI frame) {
        super();
        add(fileMenu = new FileMenu(frame));

    }

}
