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
        super("Aide");
        setFont(new Font("Verdana", Font.PLAIN, 18));
        setForeground(Color.white);

        add(help = new JMenuItem("Aide"));
        help.setFont(new Font("Verdana", Font.PLAIN, 14));
        help.setBackground(Color.white);
        help.setForeground(Color.black);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(new JFrame(), "Cliquez sur un bouton pour changer le mode d'édition" + System.lineSeparator() + "Un clic droit sur un bouton permet de le supprimer" + System.lineSeparator() + "Un clic gauche sur un bouton permet de le déplacer" + System.lineSeparator() + "Un clic gauche sur un bouton permet de le déplacer");
            }
        });

        add(about = new JMenuItem("A propos"));
        about.setFont(new Font("Verdana", Font.PLAIN, 14));
        about.setBackground(Color.white);
        about.setForeground(Color.black);
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(new JFrame(), "Créé par Clément Bourles");
            }
        });
    }
}
