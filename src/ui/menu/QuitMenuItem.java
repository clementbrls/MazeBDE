package ui.menu;

import ui.FrameUI;

import javax.swing.* ;

public class QuitMenuItem extends JMenuItem {

    private final FrameUI frame;

    public QuitMenuItem(FrameUI frame) {
        super("Quit") ; // Text of menu item

        this.frame = frame;
    }
}