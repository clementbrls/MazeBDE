package ui.menu;

import ui.FrameUI;

import javax.swing.*;

public final class FileMenu extends JMenu {
    private final QuitMenuItem quitMenuItem;
    private final SaveMenuItem saveMenuItem;
    private final OpenMenuItem openMenuItem;

    public FileMenu(FrameUI frame){
        super("File");
        quitMenuItem = new QuitMenuItem(frame);
        saveMenuItem = new SaveMenuItem(frame);
        openMenuItem = new OpenMenuItem(frame);


        add(saveMenuItem);
        add(openMenuItem);
        add(quitMenuItem);
    }
}
