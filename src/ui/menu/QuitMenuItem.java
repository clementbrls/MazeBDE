package ui.menu;

import ui.*;

import javax.swing.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitMenuItem extends JMenuItem implements ActionListener {

    private final FrameUI frame;

    public QuitMenuItem(FrameUI frame) {
        super("Quitter") ; // Text of menu item
        addActionListener(this);
        this.frame = frame;
    }

    public final void actionPerformed(ActionEvent evt){
        System.exit(0);
    }
}