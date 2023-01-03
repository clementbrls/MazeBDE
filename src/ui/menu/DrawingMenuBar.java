package ui.menu;
import ui.*;
import Maze.*;
import javax.swing.*;
import java.awt.*;

public class DrawingMenuBar extends JMenuBar {
    private FileMenu fileMenu;
    private SettingsMenu settingMenu;
    private final HelpMenu helpMenu;

    public DrawingMenuBar(FrameUI frame,Maze maze) {
        super();
        add(fileMenu = new FileMenu(frame));
        add(settingMenu = new SettingsMenu(frame));
        add(helpMenu = new HelpMenu(frame));
        setBackground(new Color(54, 65, 86));
        setForeground(Color.white);
        setPreferredSize(new Dimension(super.getWidth(),40));

    }

}
