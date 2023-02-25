package ui.menu;

import ui.FrameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RandomMenuItem extends JMenuItem implements ActionListener, KeyListener {

    private final FrameUI frame;

    public RandomMenuItem(FrameUI frame) {
        super("Randomise"); // Text of menu item
        addActionListener(this);
        this.frame = frame;
        setFont(new Font("Verdana", Font.PLAIN, 14));
        frame.addKeyListener(this);
    }

    public final void actionPerformed(ActionEvent evt) {
        frame.getModel().getMaze().randomize();
        frame.getModel().stateChanged();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_SPACE) {
            frame.getModel().randomize();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}