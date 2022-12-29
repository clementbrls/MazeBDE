package ui.button;
import ui.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartureButton extends JButton implements ActionListener {
    private final FrameUI frame;

    public DepartureButton(FrameUI frame) {
        super("Depart");
        this.frame=frame;
        addActionListener(this);
    }

    public final void actionPerformed(ActionEvent evt){
        DrawMaze.setSelect("D");
    }
}
