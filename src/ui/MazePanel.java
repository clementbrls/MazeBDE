package ui;
import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    private final FrameUI frame;

    public MazePanel(FrameUI frame){
    this.frame = frame;
        setBackground(Color.white);
        setPreferredSize(new Dimension(300,300));
    }
}
