package ui.menu;

import ui.FrameUI;

import javax.swing.*;

public class SaveMenuItem extends JMenuItem {
    private final FrameUI frame;
    public SaveMenuItem(FrameUI frame) {
        super("Sauvegarder"); // Text of menu item

        this.frame = frame;
    }
}
