package ui.menu;
import ui.*;
import Maze.*;
import javax.swing.*;
import java.awt.*;

public class DrawingMenuBar extends JMenuBar {
    private FileMenu fileMenu;
    public DrawingMenuBar(FrameUI frame,Maze maze) {
        super();
        add(fileMenu = new FileMenu(frame,maze));
        setBackground(new Color(54, 65, 86));
        setForeground(Color.white);
        setPreferredSize(new Dimension(super.getWidth(),40));

    }

}
