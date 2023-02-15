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
    private final JLabel noPath = new JLabel("No Path");
    private final Model model;

    public InfoPanel(FrameUI frame) {
       // setLayout(null);
        setFocusable(false);
        setPreferredSize(new Dimension(100, getHeight()));
        model=frame.getModel();
        model.addObserver(this);
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
        SolveMazeButton.setBackground(new Color(50, 50, 50));
        SolveMazeButton.setForeground(Color.white);
        SolveMazeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                model.doDijsktra();
            }
        });


        //Auto Dijkstra
        JCheckBox autoDijkstra = new JCheckBox("Auto Dijkstra",model.getAutoDijkstra());
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (model.getDistance() == 9999 || (model.getDistance() == -1 && !model.getAutoDijkstra())) {
            distPanel.setVisible(false);
        } else {
            if (model.getDistance() != -1)
                valueDistance.setText("" + model.getDistance());
            distPanel.setVisible(true);
            //System.out.println("Distance infoPanel : " + model.getDistance());
        }

        noPath.setVisible(model.getDistance() == 9999);


        SolveMazeButton.setVisible(!model.getAutoDijkstra());

    }


    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}