package ui.button;
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
        add(arrivalButton = new ArrivalButton(frame));
        add(departureButton = new DepartureButton(frame));
        add(emptyButton = new EmptyButton(frame));
        add(wallButton = new WallButton(frame));

    }
}
