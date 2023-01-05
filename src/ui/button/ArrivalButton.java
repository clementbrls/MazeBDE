package ui.button;
import ui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Maze.*;

public class ArrivalButton extends JButton implements ActionListener {
    private final FrameUI frame;
    private final ButtonPanel bPanel;

    public ArrivalButton(FrameUI frame,ButtonPanel bPanel){
        super("Arrival");
        setFocusable(false);
        this.frame=frame;
        this.bPanel=bPanel;
        addActionListener(this);
        setMargin(ButtonPanel.buttonMargin);

    }

    public final void actionPerformed(ActionEvent evt){
        frame.getdMaze().setSelect(ArrivalBox.Label);
        frame.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(frame.getdMaze().getSelect() == ArrivalBox.Label){
            setBackground(DrawMaze.colorArrival);
            setForeground(Color.white);
        } else {
            setBackground(DrawMaze.colorDefault);
            setForeground(Color.black);
        }
        setFont(bPanel.getFontButton());
    }

}
