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

    public InfoPanel(FrameUI frame){
        this.frame=frame;
        setLayout(new BorderLayout());
        setFocusable(false);
        setPreferredSize(new Dimension(100,getHeight()));

        //Distance
        JLabel distance = new JLabel("Distance : ");
        distPanel = new JPanel();
        distPanel.add(distance);
        distPanel.add(valueDistance);
        distance.setFocusable(false);
        distance.setBackground(Color.white);
        add(distPanel,BorderLayout.NORTH);

        //Solve maze button
        JPanel bPanel = new JPanel();
        SolveMazeButton=new JButton("Solve");
        bPanel.add(SolveMazeButton);
        SolveMazeButton.setFocusable(false);
        add(bPanel,BorderLayout.CENTER);
        SolveMazeButton.setSize(new Dimension(getWidth(),200));

        SolveMazeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               frame.getDrawMaze().getMaze().dijkstra();
               frame.repaint();
            }
        });

    }

    public void paintComponent(Graphics g){
        valueDistance.setText(""+frame.getDrawMaze().getDistance());
        if(frame.getDrawMaze().getDistance() == 999){
            distPanel.setVisible(false);
        } else {
           distPanel.setVisible(true);
        }

        if (frame.getDrawMaze().getAutoDijkstra()) {
            SolveMazeButton.setVisible(false);

        } else {
            SolveMazeButton.setVisible(true);
        }

    }


}