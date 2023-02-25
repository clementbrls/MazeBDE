package ui.menu;

import maze.Maze;
import ui.FrameUI;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {

    public MenuBar(FrameUI frame, Maze maze) {
        super();
        add(new FileMenu(frame));
        add(new HelpMenu(frame));
        setBackground(new Color(54, 65, 86));
        setForeground(Color.white);
        setPreferredSize(new Dimension(super.getWidth(), 40));

    }

}
