package ui.menu;
import ui.*;
import Maze.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JMenu {
    private JMenuItem autoDijkstra;
    private JMenuItem hideInfoPanel;
    private FrameUI frame;
    public SettingsMenu(FrameUI frame) {
        super("Settings");
        this.frame=frame;
        setFont(new Font("Verdana",Font.PLAIN,18));
        setForeground(Color.white);

        add(autoDijkstra = new JMenuItem("Auto Dijkstra"));
        autoDijkstra.setFont(new Font("Verdana",Font.PLAIN,14));
        autoDijkstra.setBackground(Color.white);
        autoDijkstra.setForeground(Color.black);
        autoDijkstra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.getdMaze().setAutoDijkstra(!frame.getdMaze().getAutoDijkstra());

                if(!frame.getdMaze().getAutoDijkstra()) frame.getdMaze().setInfoPanelVisibility(true);
                frame.repaint();
            }
        });

        add(hideInfoPanel = new JMenuItem("Hide infos"));
        hideInfoPanel.setFont(new Font("Verdana",Font.PLAIN,14));
        hideInfoPanel.setBackground(Color.white);
        hideInfoPanel.setForeground(Color.black);
        hideInfoPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFrame jFrame = new JFrame();
                int result = JOptionPane.showConfirmDialog(jFrame, "Hide infos?");
                if (result == 0) {
                    frame.getdMaze().setAutoDijkstra(true);
                    frame.getdMaze().setInfoPanelVisibility(false);

                }
                else if (result == 1) {
                    frame.getdMaze().setInfoPanelVisibility(true);
                }
                frame.repaint();
                frame.pack();
            }
        });
    }
}
