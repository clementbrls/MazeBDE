package ui.menu;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JMenu {
    private JMenuItem autoDijkstra;
    public SettingsMenu(FrameUI frame) {
        super("Paramètre");
        setFont(new Font("Verdana",Font.PLAIN,18));
        setForeground(Color.white);

        add(autoDijkstra = new JMenuItem("Auto Dijkstra"));
        autoDijkstra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFrame jFrame = new JFrame();
                int result = JOptionPane.showConfirmDialog(jFrame, "Dijkstra automatique ?");
                if (result == 0) frame.getdMaze().setAutoDijkstra(true);
                else if (result == 1) frame.getdMaze().setAutoDijkstra(false);
                frame.repaint();
            }
        });

    }
}
