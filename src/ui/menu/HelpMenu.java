package ui.menu;

import ui.FrameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                JOptionPane.showMessageDialog(new JFrame(), "Cliquez sur un bouton pour changer le mode d'édition" + System.lineSeparator() + "Un clic droit permet de transformer un mur en case vide" + System.lineSeparator() + "Un clic gauche permet de transformer une case en le type de case sélectionné" + System.lineSeparator() + "Un appui sur la touche 'Espace' permet de générer un labyrinthe aléatoire");
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
