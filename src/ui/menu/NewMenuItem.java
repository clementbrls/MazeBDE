package ui.menu;
import Maze.*;
import ui.*;

import javax.swing.* ;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMenuItem extends JMenuItem implements ActionListener {

    private final FrameUI frame;

    public NewMenuItem(FrameUI frame) {
        super("Nouveau") ; // Text of menu item
        addActionListener(this);
        this.frame = frame;
    }

    public final void actionPerformed(ActionEvent evt){
        /*frame.dispose();
        JFrame jFrame = new JFrame();
        String getMessage = JOptionPane.showInputDialog(jFrame, "Enter your message");
*/
        JSpinner newHeight = new JSpinner(new SpinnerNumberModel(10,2,20,1));
        JSpinner newWidth = new JSpinner(new SpinnerNumberModel(10,2,20,1));
        Object[] message = {
                "Hauteur : ", newHeight,
                "Largeur : ", newWidth,
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Nouveau labyrinthe", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int newHeightInt = (int) newHeight.getValue();
            int newWidthInt = (int) newWidth.getValue();
            Maze maze = new Maze(newHeightInt, newWidthInt);
            frame.dispose();
            new FrameUI(maze);
        }
    }
}