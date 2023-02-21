package ui.menu;

import maze.*;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMenuItem extends JMenuItem implements ActionListener {

    private final FrameUI frame;

    public NewMenuItem(FrameUI frame) {
        super("New"); //Texte du menu
        addActionListener(this);
        this.frame = frame;
        setFont(new Font("Verdana", Font.PLAIN, 14));
    }

    public final void actionPerformed(ActionEvent evt) {
        JSpinner newHeight = new JSpinner(new SpinnerNumberModel(10, 2, 50, 1));//limite la valeur max
        JSpinner newWidth = new JSpinner(new SpinnerNumberModel(10, 2, 50, 1));
        Object[] message = {
                "Hauteur : ", newHeight,
                "Largeur : ", newWidth,
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Nouveau labyrinthe", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int newHeightInt = (int) newHeight.getValue();
            int newWidthInt = (int) newWidth.getValue();
            Maze maze = new Maze(newHeightInt, newWidthInt);//Créer un nouveau labyrinthe
            frame.dispose();//Destroy the frame
            new FrameUI(maze);//Créer une nouvelle fenêtre avec le nouveau labyrinthe
        }
    }
}