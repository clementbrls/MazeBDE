package ui.button;

import maze.DepartureBox;
import ui.FrameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartureButton extends JButton implements ActionListener {
    private final FrameUI frame;
    private final ButtonPanel bPanel;

    public DepartureButton(FrameUI frame, ButtonPanel bPanel) {
        super("Départ");
        setFocusable(false);
        this.frame = frame;
        this.bPanel = bPanel;
        addActionListener(this);
        setMargin(ButtonPanel.buttonMargin);
    }

    public final void actionPerformed(ActionEvent evt) {
        frame.getModel().setSelect(DepartureBox.Label);
    }

    public void stateChanged() {
        if (frame.getModel().getSelect() == DepartureBox.Label) {
            setBackground(DepartureBox.color);
            setForeground(Color.white);
        } else {
            setBackground(ButtonPanel.colorDefault);
            setForeground(Color.black);
        }
        setFont(bPanel.getFontButton());
    }
}
