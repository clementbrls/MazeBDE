package ui.button;
import ui.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArrivalButton extends JButton implements ActionListener {
    private final FrameUI frame;

    public ArrivalButton(FrameUI frame){
        super("Arrivée");
        this.frame=frame;
        addActionListener(this);
    }

    public final void actionPerformed(ActionEvent evt){
        DrawMaze.setSelect("A");
    }

}
