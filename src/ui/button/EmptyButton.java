package ui.button;
import ui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Maze.*;

public class EmptyButton extends JButton implements ActionListener {

    private final FrameUI frame;
    private final ButtonPanel bPanel;

    public EmptyButton(FrameUI frame,ButtonPanel bPanel) {
        super("Wipe");
        this.frame=frame;
        this.bPanel=bPanel;
        setFocusable(false);
        addActionListener(this);
        setMargin(ButtonPanel.buttonMargin);
    }

    public final void actionPerformed(ActionEvent evt){
        frame.getModel().setSelect(EmptyBox.Label);
    }

    public void stateChanged() {
        if(frame.getModel().getSelect() == EmptyBox.Label){
            setBackground(WallBox.color);
            setForeground(Color.white);
        } else {
            setBackground(ButtonPanel.colorDefault);
            setForeground(Color.black);
        }
        setFont(bPanel.getFontButton());
    }
}
