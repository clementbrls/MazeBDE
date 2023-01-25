package ui.menu;
import ui.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HelpMenu extends JMenu {
    private final JMenuItem help;
    private final JMenuItem about;
    public HelpMenu(FrameUI frame) {
        super("Help");
        setFont(new Font("Verdana", Font.PLAIN, 18));
        setForeground(Color.white);

        add(help = new JMenuItem("Help"));
        help.setFont(new Font("Verdana",Font.PLAIN,14));
        help.setBackground(Color.white);
        help.setForeground(Color.black);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(new JFrame(), "Click on a button to select the mode of edition"+System.lineSeparator()+"A right click allow you to quicly erase a wall");
            }
        });

        add(about = new JMenuItem("About"));
        about.setFont(new Font("Verdana",Font.PLAIN,14));
        about.setBackground(Color.white);
        about.setForeground(Color.black);
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(new JFrame(),"Created by Clément Bourles");
            }
        });
    }
}
