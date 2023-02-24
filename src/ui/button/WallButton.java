package ui.button;

import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maze.*;

public class WallButton extends JButton implements ActionListener {
    private final FrameUI frame;
    private final ButtonPanel bPanel;

    public WallButton(FrameUI frame, ButtonPanel bPanel) {
        super("Mur");
        this.frame = frame;
        this.bPanel = bPanel;
        setFocusable(false);
        addActionListener(this);
        setMargin(ButtonPanel.buttonMargin);
    }

    public final void actionPerformed(ActionEvent evt) {
        frame.getModel().setSelect(WallBox.Label);
    }

    public void stateChanged() {
        if (frame.getModel().getSelect() == WallBox.Label) {
            setBackground(WallBox.color);
            setForeground(Color.white);
        } else {
            setBackground(ButtonPanel.colorDefault);
            setForeground(Color.BLACK);
        }
        setFont(bPanel.getFontButton());
    }
}
