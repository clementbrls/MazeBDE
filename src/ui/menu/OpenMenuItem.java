package ui.menu;

import ui.FrameUI;

import javax.swing.*;

public class OpenMenuItem extends JMenuItem {

    private final FrameUI frame;

    public OpenMenuItem(FrameUI frame) {
        super("Ouvrir") ; // Text of menu item

        this.frame = frame;
    }
}
