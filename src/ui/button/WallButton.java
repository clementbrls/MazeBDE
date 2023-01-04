package ui.button;
import ui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WallButton extends JButton implements ActionListener {
    private final FrameUI frame;
    private final ButtonPanel bPanel;

    public WallButton(FrameUI frame,ButtonPanel bPanel) {
        super("Wall");
        this.frame=frame;
        this.bPanel=bPanel;
        setFocusable(false);
        addActionListener(this);
        setMargin(ButtonPanel.buttonMargin);
    }

    public final void actionPerformed(ActionEvent evt){
        frame.getdMaze().setSelect("W");
        frame.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(frame.getdMaze().getSelect() == "W"){
            setBackground(DrawMaze.colorWall);
            setForeground(Color.white);
        } else {
            setBackground(DrawMaze.colorDefault);
            setForeground(Color.BLACK);
        }
        setFont(bPanel.getFontButton());
    }
}
