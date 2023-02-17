package ui.menu;
import Maze.*;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class SaveMenuItem extends JMenuItem implements ActionListener {
    private final FrameUI frame;
    private final Maze maze;
    public SaveMenuItem(FrameUI frame) {
        super("Save"); // Text of menu item
        addActionListener(this);
        this.maze=frame.getModel().getMaze();
        this.frame = frame;
        setFont(new Font("Verdana",Font.PLAIN,14));
    }

    public final void actionPerformed(ActionEvent evt){
        JFrame jFrame = new JFrame();
        String getNameFile = JOptionPane.showInputDialog(jFrame, "Give a name to the maze");
        getNameFile = getNameFile.replaceAll("/","");// Pour eviter les erreurs de chemin
        getNameFile = getNameFile.replaceAll("\\.","");
        String file = "data/"+getNameFile+".maze";
        maze.saveToTextFile(file);

    }
}
