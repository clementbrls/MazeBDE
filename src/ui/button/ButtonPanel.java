package ui.button;
import ui.DrawMaze;
import ui.FrameUI;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private final ArrivalButton arrivalButton;
    private final DepartureButton departureButton;
    private final EmptyButton emptyButton;
    private final WallButton wallButton;
    public ButtonPanel(FrameUI frame){
        setLayout(new GridLayout(1,4));

        add(departureButton = new DepartureButton(frame,this));
        add(arrivalButton = new ArrivalButton(frame,this));
        add(emptyButton = new EmptyButton(frame,this));
        add(wallButton = new WallButton(frame,this));
        setPreferredSize(new Dimension(getWidth(),60));
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
}
