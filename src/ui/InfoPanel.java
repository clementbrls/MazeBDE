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
        setLayout(new BorderLayout());
        setFocusable(false);
        setPreferredSize(new Dimension(100, getHeight()));

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

        //NoPath
        add(noPath, BorderLayout.SOUTH);


        SolveMazeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.getdMaze().getMaze().dijkstra();
                frame.repaint();
            }
        });

    }

    public void paintComponent(Graphics g) {

        if (frame.getdMaze().getDistance() == 999 || (frame.getdMaze().getDistance() == -1 && !frame.getdMaze().getAutoDijkstra())) {
            distPanel.setVisible(false);
        } else {
            if (frame.getdMaze().getDistance() != -1)
                valueDistance.setText("" + frame.getdMaze().getDistance());
            distPanel.setVisible(true);
        }

        noPath.setVisible(frame.getdMaze().getDistance() == 999);


        SolveMazeButton.setVisible(!frame.getdMaze().getAutoDijkstra());

        setVisible(frame.getdMaze().getInfoPanelVisibility());
        frame.pack();
    }


}