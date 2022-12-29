package ui.menu;
import ui.*;
import Maze.*;
import javax.swing.*;
import java.awt.*;

public final class FileMenu extends JMenu {
    private final QuitMenuItem quitMenuItem;
    private final SaveMenuItem saveMenuItem;
    private final OpenMenuItem openMenuItem;
    private final NewMenuItem newMenuItem;

    public FileMenu(FrameUI frame,Maze maze){
        super("Fichier");
        setFont(new Font("Verdana",Font.PLAIN,18));
        setForeground(Color.white);

        quitMenuItem = new QuitMenuItem(frame);
        saveMenuItem = new SaveMenuItem(frame,maze);
        openMenuItem = new OpenMenuItem(frame,maze);
        newMenuItem = new NewMenuItem(frame);

        quitMenuItem.setBackground(DrawMaze.colorWall);
        openMenuItem.setBackground(DrawMaze.colorWall);
        saveMenuItem.setBackground(DrawMaze.colorWall);;
        newMenuItem.setBackground(DrawMaze.colorWall);
        quitMenuItem.setForeground(Color.white);
        openMenuItem.setForeground(Color.white);
        saveMenuItem.setForeground(Color.white);;
        newMenuItem.setForeground(Color.white);

        add(newMenuItem);
        add(saveMenuItem);
        add(openMenuItem);
        add(quitMenuItem);
    }
}
