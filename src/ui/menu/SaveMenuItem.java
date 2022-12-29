package ui.menu;
import Maze.*;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class SaveMenuItem extends JMenuItem implements ActionListener {
    private final FrameUI frame;
    private Maze maze;
    public SaveMenuItem(FrameUI frame,Maze maze) {
        super("Sauvegarder"); // Text of menu item
        addActionListener(this);
        this.maze=maze;
        this.frame = frame;
        setFont(new Font("Verdana",Font.PLAIN,12));
    }

    public final void actionPerformed(ActionEvent evt){
        JFrame jFrame = new JFrame();
        String getNameFile = JOptionPane.showInputDialog(jFrame, "Donnez un nom au labyrinthe");
        String file = "data/"+getNameFile+".maze";
        maze.saveToTextFile(file);

    }
}
