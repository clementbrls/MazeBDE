package ui;

import maze.Maze;
import ui.menu.MenuBar;

import javax.swing.*;

public class FrameUI extends JFrame {
    private final MenuBar menuBar;
    private final WindowPanel windowPanel;


    private final Model model;

    public FrameUI(Maze maze) {
        super("Labyrinthe de Clément Bourles"); // Window title
        ImageIcon img = new ImageIcon("data/icon.png");//Icone de la fenetre
        setIconImage(img.getImage());

        this.model = new Model(maze);

        //Affichage de la barre de menu
        menuBar = new MenuBar(this, maze);
        setJMenuBar(menuBar);
        //Affichage du panneau principal (qui contient les autres panneaux)
        setContentPane(windowPanel = new WindowPanel(this));

        //do the method quit() when the user click on the red cross
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                quit();
            }
        });

        pack();// Components sizes and positions
        setLocationRelativeTo(null);//Center frame on the screen
        setVisible(true);  // The great show
    }

    public Model getModel() {
        return model;
    }

    public void quit() {
        //Ouvre une fenetre de confirmation pour quitter sans sauvegarder
        UIManager.put("OptionPane.yesButtonText", "Oui");
        UIManager.put("OptionPane.noButtonText", "Non");
        if(model.isSave()){
            System.exit(0);
        } else {
            int result = JOptionPane.showConfirmDialog(this, "Le labyrinthe n'est pas sauvegardé" + System.lineSeparator() + "Etes vous sûr de vouloir quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}