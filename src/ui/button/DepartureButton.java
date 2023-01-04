package ui.button;
import ui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartureButton extends JButton implements ActionListener {
    private final FrameUI frame;
    private final ButtonPanel bPanel;

    public DepartureButton(FrameUI frame,ButtonPanel bPanel) {
        super("Departure");
        setFocusable(false);
        this.frame=frame;
        this.bPanel=bPanel;
        addActionListener(this);
        setMargin(ButtonPanel.buttonMargin);
    }

    public final void actionPerformed(ActionEvent evt){
        frame.getdMaze().setSelect("D");
        frame.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(frame.getdMaze().getSelect() == "D"){
            setBackground(DrawMaze.colorDeparture);
            setForeground(Color.white);
        } else {
            setBackground(DrawMaze.colorDefault);
            setForeground(Color.black);
        }
        setFont(bPanel.getFontButton());
    }
}
