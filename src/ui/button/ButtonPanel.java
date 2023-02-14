package ui.button;
import ui.DrawMaze;
import ui.FrameUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class ButtonPanel extends JPanel implements ChangeListener {
    public static final Insets buttonMargin = new Insets(10,10,10,10);
    public static final Color colorDefault = new Color(241, 243, 244);
    private final ArrivalButton arrivalButton;
    private final DepartureButton departureButton;
    private final EmptyButton emptyButton;
    private final WallButton wallButton;
    public ButtonPanel(FrameUI frame){
        //setLayout(new BorderLayout());
        frame.getModel().addObserver(this);
        add(departureButton = new DepartureButton(frame,this));
        add(arrivalButton = new ArrivalButton(frame,this));
        add(emptyButton = new EmptyButton(frame,this));
        add(wallButton = new WallButton(frame,this));
        //setPreferredSize(new Dimension(getWidth(),60));
    }


    protected Font getFontButton(){
        Font font;
        //System.out.println(getWidth());
        if (getWidth() < 460 && getWidth() > 340) {
            font = new Font("Verdana",Font.PLAIN,12);
        } else if (getWidth() < 340){
            font = new Font("Verdana",Font.PLAIN,9);
        } else {
            font = new Font("Verdana",Font.PLAIN,20);
        }
        return font;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        departureButton.repaint();
        arrivalButton.repaint();
        emptyButton.repaint();
        wallButton.repaint();
    }
}
