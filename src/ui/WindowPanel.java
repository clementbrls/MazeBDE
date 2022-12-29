package ui;
import Maze.*;
import ui.button.*;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {
    private MazePanel mazePanel;
    private final ButtonPanel bPanel;

    public WindowPanel(FrameUI frame,Maze maze){
        setLayout(new BorderLayout());
        add(mazePanel = new MazePanel(frame,maze),BorderLayout.CENTER);
        add(bPanel = new ButtonPanel(frame),BorderLayout.SOUTH);
    }


}
