package ui.menu;

import ui.*;
import maze.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenMenuItem extends JMenuItem implements ActionListener {

    private final FrameUI frame;
    private final Maze maze;

    public OpenMenuItem(FrameUI frame) {
        super("Open"); // Text of menu item
        addActionListener(this);
        this.maze = frame.getModel().getMaze();
        this.frame = frame;
        setFont(new Font("Verdana", Font.PLAIN, 14));
    }

    public final void actionPerformed(ActionEvent evt) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("./data"));
        fc.setDialogTitle("Selectionnez un labyrinthe");
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Fichier maze", "maze"));
        JFrame jFrame = new JFrame();
        int result = fc.showOpenDialog(jFrame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //System.out.println("Fichier : " + file.getName());
            try {
                maze.initFromTextFile(file.getPath());
            } catch (MazeReadingException e) {
                JOptionPane.showMessageDialog(jFrame, e.getMessage());
            }
            frame.dispose();//Destroy the frame
            new FrameUI(maze);
        }
    }
}
