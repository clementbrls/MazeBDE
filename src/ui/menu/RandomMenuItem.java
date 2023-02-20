package ui.menu;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class RandomMenuItem extends JMenuItem implements ActionListener, KeyListener {

    private final FrameUI frame;

    public RandomMenuItem(FrameUI frame) {
        super("Randomize") ; // Text of menu item
        addActionListener(this);
        this.frame = frame;
        setFont(new Font("Verdana",Font.PLAIN,14));
        frame.addKeyListener(this);
    }

    public final void actionPerformed(ActionEvent evt){
        frame.getModel().getMaze().randomize();
        frame.getModel().stateChanged();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            frame.getModel().getMaze().randomize();
            frame.getModel().stateChanged();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}