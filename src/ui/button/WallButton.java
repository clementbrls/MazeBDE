package ui.button;
import ui.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WallButton extends JButton implements ActionListener {
    private final FrameUI frame;

    public WallButton(FrameUI frame) {
        super("Mur");
        this.frame=frame;
        addActionListener(this);
    }

    public final void actionPerformed(ActionEvent evt){
        DrawMaze.setSelect("W");
    }
}
