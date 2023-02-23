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
        super("Ouvrir"); // Text of menu item
        addActionListener(this);
        this.maze = frame.getModel().getMaze();
        this.frame = frame;
        setFont(new Font("Verdana", Font.PLAIN, 14));
    }

    public final void actionPerformed(ActionEvent evt) {
        //mettre le FileChooser en français :
        UIManager.put("FileChooser.openDialogTitleText", "Ouvrir un fichier");
        UIManager.put("FileChooser.lookInLabelText", "Chercher dans :");
        UIManager.put("FileChooser.saveInLabelText", "Enregistrer dans :");
        UIManager.put("FileChooser.cancelButtonText", "Annuler");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Annuler");
        UIManager.put("FileChooser.openButtonText", "Ouvrir");
        UIManager.put("FileChooser.openButtonToolTipText", "Ouvrir");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Type de fichier :");
        UIManager.put("FileChooser.fileNameLabelText", "Nom du fichier :");
        UIManager.put("FileChooser.saveButtonText", "Enregistrer");
        UIManager.put("FileChooser.saveButtonToolTipText", "Enregistrer");
        UIManager.put("FileChooser.saveDialogTitleText", "Enregistrer un fichier");

        //File chooser :
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("./data"));//On ouvre le fileChooser dans le dossier data
        fc.setDialogTitle("Selectionnez un labyrinthe");
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Fichier maze", "maze"));//On ne peut ouvrir que les fichiers .maze
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
