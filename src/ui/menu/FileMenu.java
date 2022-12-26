package ui.menu;

import ui.MazeUI;

import javax.swing.*;

public final class FileMenu extends JMenu {
    private final QuitMenuItem quitMenuItem;
    private final SaveMenuItem saveMenuItem;
    private final OpenMenuItem openMenuItem;

    public FileMenu(MazeUI DApp){
        quitMenuItem = new QuitMenuItem(DApp);
        saveMenuItem = new SaveMenuItem(DApp);
        openMenuItem = new OpenMenuItem(DApp);

        add(quitMenuItem);
        add(saveMenuItem);
        add(openMenuItem);
    }
}
