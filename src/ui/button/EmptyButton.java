package ui.button;
import ui.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmptyButton extends JButton implements ActionListener {

    private final FrameUI frame;

    public EmptyButton(FrameUI frame) {
        super("Case Vide");
        this.frame=frame;
        addActionListener(this);
    }

    public final void actionPerformed(ActionEvent evt){
        DrawMaze.setSelect("E");
    }
}
