package ui.button;
import ui.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArrivalButton extends JButton implements ActionListener {
    private final FrameUI frame;
    private final ButtonPanel bPanel;

    public ArrivalButton(FrameUI frame,ButtonPanel bPanel){
        super("Arrivée");
        setFocusable(false);
        this.frame=frame;
        this.bPanel=bPanel;
        addActionListener(this);
        setMargin(new Insets(0,0,0,0));

    }

    public final void actionPerformed(ActionEvent evt){
        frame.getDrawMaze().setSelect("A");
        frame.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(frame.getDrawMaze().getSelect() == "A"){
            setBackground(DrawMaze.colorArrival);
            setForeground(Color.white);
        } else {
            setBackground(DrawMaze.colorDefault);
            setForeground(Color.black);
        }
        setFont(bPanel.getFontButton());
    }

}
