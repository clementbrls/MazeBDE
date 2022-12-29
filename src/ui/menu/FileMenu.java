package ui.menu;
import ui.*;
import Maze.*;
import javax.swing.*;

public final class FileMenu extends JMenu {
    private final QuitMenuItem quitMenuItem;
    private final SaveMenuItem saveMenuItem;
    private final OpenMenuItem openMenuItem;
    private final NewMenuItem newMenuItem;

    public FileMenu(FrameUI frame,Maze maze){
        super("File");
        quitMenuItem = new QuitMenuItem(frame);
        saveMenuItem = new SaveMenuItem(frame,maze);
        openMenuItem = new OpenMenuItem(frame,maze);
        newMenuItem = new NewMenuItem(frame);

        add(newMenuItem);
        add(saveMenuItem);
        add(openMenuItem);
        add(quitMenuItem);
    }
}
