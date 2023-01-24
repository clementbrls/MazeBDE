package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoPanel extends JPanel {
    private JLabel valueDistance = new JLabel("1");
    private JPanel distPanel;
    private FrameUI frame;
    private JButton SolveMazeButton;
    private JLabel noPath = new JLabel("No Path");

    public InfoPanel(FrameUI frame) {
        this.frame = frame;
       // setLayout(null);
        setFocusable(false);
        setPreferredSize(new Dimension(100, getHeight()));

        //NoPath
        add(noPath);

        //Distance
        JLabel distance = new JLabel("Distance : ");
        distPanel = new JPanel();
        distPanel.add(distance);
        distPanel.add(valueDistance);
        distance.setFocusable(false);
        distance.setBackground(Color.white);
        add(distPanel, BorderLayout.NORTH);


        //Solve maze button
        JPanel bPanel = new JPanel();
        SolveMazeButton = new JButton("Solve");
        bPanel.add(SolveMazeButton);
        SolveMazeButton.setFocusable(false);
        add(bPanel, BorderLayout.CENTER);
        SolveMazeButton.setSize(new Dimension(getWidth(), 200));




        SolveMazeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.getModel().getMaze().dijkstra();
                frame.repaint();
            }
        });

        JCheckBox autoDijkstra = new JCheckBox("Auto Dijkstra",frame.getModel().getAutoDijkstra());
        add(autoDijkstra, BorderLayout.CENTER);
        autoDijkstra.setFocusable(false);
        autoDijkstra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.getModel().setAutoDijkstra(autoDijkstra.isSelected());
                frame.repaint();
            }
        });


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (frame.getModel().getDistance() == 999 || (frame.getModel().getDistance() == -1 && !frame.getModel().getAutoDijkstra())) {
            distPanel.setVisible(false);
        } else {
            if (frame.getModel().getDistance() != -1)
                valueDistance.setText("" + frame.getModel().getDistance());
            distPanel.setVisible(true);
        }

        noPath.setVisible(frame.getModel().getDistance() == 999);


        SolveMazeButton.setVisible(!frame.getModel().getAutoDijkstra());

    }


}