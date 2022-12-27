package ui;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {
    private final MazePanel mazePanel;
    private final ButtonPanel bPanel;
    public WindowPanel(FrameUI frame){
        setLayout(new BorderLayout());
        add(mazePanel = new MazePanel(frame),BorderLayout.CENTER);
        add(bPanel = new ButtonPanel(frame),BorderLayout.SOUTH);
    }


}
