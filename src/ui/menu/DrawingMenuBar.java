package ui.menu;
import ui.*;
import Maze.*;
import javax.swing.*;

public class DrawingMenuBar extends JMenuBar {
    private FileMenu fileMenu;
    public DrawingMenuBar(FrameUI frame,Maze maze) {
        super();
        add(fileMenu = new FileMenu(frame,maze));

    }

}
