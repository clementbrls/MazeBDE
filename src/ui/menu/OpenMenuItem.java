package ui.menu;

import ui.*;
import Maze.*;
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
        super("Ouvrir") ; // Text of menu item
        addActionListener(this);
        this.maze=frame.getDrawMaze().getMaze();
        this.frame = frame;
        setFont(new Font("Verdana",Font.PLAIN,12));
    }

    public final void actionPerformed(ActionEvent evt){
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("./data"));
        fc.setDialogTitle("Selectionnez un labyrinthe");
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Fichier maze", "maze"));
        JFrame jFrame = new JFrame();
        int result = fc.showOpenDialog(jFrame);

        if(result == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            System.out.println("Fichier : "+file.getName());
            maze.initFromTextFile("data/"+file.getName());
            frame.dispose();//Destroy the frame
            new FrameUI(maze);
        }
    }
}
