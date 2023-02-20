package ui.button;

import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import maze.*;

public class ArrivalButton extends JButton implements ActionListener {
    private final FrameUI frame;
    private final ButtonPanel bPanel;

    public ArrivalButton(FrameUI frame, ButtonPanel bPanel) {
        super("Arrival");
        setFocusable(false);
        this.frame = frame;
        this.bPanel = bPanel;
        addActionListener(this);
        setMargin(ButtonPanel.buttonMargin);
    }

    public final void actionPerformed(ActionEvent evt) {
        frame.getModel().setSelect(ArrivalBox.Label);
    }

    public void stateChanged() {
        if (frame.getModel().getSelect() == ArrivalBox.Label) {
            setBackground(ArrivalBox.color);
            setForeground(Color.white);
        } else {
            setBackground(ButtonPanel.colorDefault);
            setForeground(Color.black);
        }
        setFont(bPanel.getFontButton());
    }

}
