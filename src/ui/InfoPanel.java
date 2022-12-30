package ui;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    JLabel valueDistance = new JLabel("1");
    FrameUI frame;

    public InfoPanel(FrameUI frame){
        this.frame=frame;
        setLayout(new BorderLayout());
        setFocusable(false);
        setPreferredSize(new Dimension(100,getHeight()));

        JLabel distance = new JLabel("Distance : ");
        JPanel distPanel = new JPanel();
        distPanel.add(distance);
        distPanel.add(valueDistance);
        distance.setFocusable(false);
        distance.setBackground(Color.white);

        add(distPanel,BorderLayout.NORTH);
        JPanel bPanel = new JPanel();
        JButton SolveMazeButton=new JButton("Solve");
        bPanel.add(SolveMazeButton);
        SolveMazeButton.setFocusable(false);

        add(bPanel,BorderLayout.CENTER);
        SolveMazeButton.setSize(new Dimension(getWidth(),200));

    }

    public void paintComponent(Graphics g){
        valueDistance.setText(""+frame.getDrawMaze().getDistance());

    }


}