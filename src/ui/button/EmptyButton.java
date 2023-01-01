package ui.button;
import ui.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmptyButton extends JButton implements ActionListener {

    private final FrameUI frame;
    private final ButtonPanel bPanel;

    public EmptyButton(FrameUI frame,ButtonPanel bPanel) {
        super("Effacer");
        this.frame=frame;
        this.bPanel=bPanel;
        setFocusable(false);
        addActionListener(this);
        setMargin(new Insets(0,0,0,0));
    }

    public final void actionPerformed(ActionEvent evt){
        frame.getdMaze().setSelect("E");
        frame.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(frame.getdMaze().getSelect() == "E"){
            setBackground(DrawMaze.colorWall);
            setForeground(Color.white);
        } else {
            setBackground(DrawMaze.colorDefault);
            setForeground(Color.black);
        }
        setFont(bPanel.getFontButton());
    }
}
