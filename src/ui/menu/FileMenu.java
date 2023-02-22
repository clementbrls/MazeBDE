package ui.menu;

import ui.*;

import javax.swing.*;
import java.awt.*;

public final class FileMenu extends JMenu {
    private final QuitMenuItem quitMenuItem;
    private final SaveMenuItem saveMenuItem;
    private final OpenMenuItem openMenuItem;
    private final NewMenuItem newMenuItem;
    private final RandomMenuItem randomMenuItem;

    public FileMenu(FrameUI frame) {
        super("Fichier");
        setFont(new Font("Verdana", Font.PLAIN, 18));
        setForeground(Color.white);
        // setPreferredSize(new Dimension(80,getHeight()));
        quitMenuItem = new QuitMenuItem(frame);
        saveMenuItem = new SaveMenuItem(frame);
        openMenuItem = new OpenMenuItem(frame);
        newMenuItem = new NewMenuItem(frame);
        randomMenuItem = new RandomMenuItem(frame);

        quitMenuItem.setBackground(Color.white);
        openMenuItem.setBackground(Color.white);
        saveMenuItem.setBackground(Color.white);
        newMenuItem.setBackground(Color.white);
        randomMenuItem.setBackground(Color.white);
        quitMenuItem.setForeground(Color.black);
        openMenuItem.setForeground(Color.black);
        saveMenuItem.setForeground(Color.black);
        newMenuItem.setForeground(Color.black);
        randomMenuItem.setForeground(Color.black);

        add(newMenuItem);
        add(saveMenuItem);
        add(openMenuItem);
        add(randomMenuItem);
        add(quitMenuItem);
    }
}
