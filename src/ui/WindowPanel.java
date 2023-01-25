package ui;
import Maze.*;
import ui.button.*;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {
    private final MazePanel mazePanel;
    private final ButtonPanel bPanel;
    private final InfoPanel infoPanel;

    public WindowPanel(FrameUI frame){
        setLayout(new BorderLayout());
        add(mazePanel = new MazePanel(frame),BorderLayout.CENTER);
        add(bPanel = new ButtonPanel(frame),BorderLayout.SOUTH);
        add(infoPanel = new InfoPanel(frame),BorderLayout.EAST);
    }

    protected void repaintInfo(){
        infoPanel.repaint();
    }


}
