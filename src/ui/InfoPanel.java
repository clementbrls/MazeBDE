package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoPanel extends JPanel implements ChangeListener {
    private final JLabel valueDistance = new JLabel("1");
    private final JPanel distPanel;
    private final JButton SolveMazeButton;
    private final Model model;
    private final JLabel noPath;

    public InfoPanel(FrameUI frame) {
        setFocusable(false);
        setPreferredSize(new Dimension(120, getHeight()));
        model = frame.getModel();
        model.addObserver(this);//Ajoute le panel comme observer du model

        //-----NoPath JLabel-----
        add(noPath = new JLabel("No Path"));

        //-----Distance JLabel-----
        JLabel distance = new JLabel("Distance : ");
        distPanel = new JPanel();
        distPanel.add(distance);
        distPanel.add(valueDistance);
        distance.setFocusable(false);//permet d'empêcher le focus sur le label (pas de bordure bleue)
        distance.setBackground(Color.white);
        add(distPanel, BorderLayout.NORTH);


        //-----Solve maze button-----
        JPanel bPanel = new JPanel();
        SolveMazeButton = new JButton("Solve");
        bPanel.add(SolveMazeButton);
        SolveMazeButton.setFocusable(false);
        add(bPanel, BorderLayout.CENTER);
        SolveMazeButton.setSize(new Dimension(getWidth(), 200));
        SolveMazeButton.setBackground(new Color(50, 50, 50));
        SolveMazeButton.setForeground(Color.white);
        SolveMazeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                model.doDijsktra();
            }
        });


        //-----Auto Dijkstra-----
        JCheckBox autoDijkstra = new JCheckBox("Auto Dijkstra", model.getAutoDijkstra());
        //change background color of only the box to white
        autoDijkstra.setBackground(Color.white);
        add(autoDijkstra, BorderLayout.CENTER);
        autoDijkstra.setFocusable(false);
        autoDijkstra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                model.setAutoDijkstra(autoDijkstra.isSelected());
            }
        });

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (model.getDistance() == 9999 || (!model.getMaze().getPath().isPath() && !model.getAutoDijkstra())) {
            distPanel.setVisible(false);//Si pas de chemin, ou que le chemin n'est pas connu et que l'auto dijkstra est désactivé, on cache distance
        } else {
            if (model.getDistance() != -1) {
                valueDistance.setText("" + model.getDistance());
            }
            distPanel.setVisible(true);
        }

        noPath.setVisible(model.getDistance() == 9999);


        SolveMazeButton.setVisible(!model.getAutoDijkstra());//Le bouton pour effectuer le dijkstra n'est visible que si l'auto dijkstra est désactivé

    }

    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}