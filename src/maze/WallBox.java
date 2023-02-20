package maze;

import java.awt.*;

public class WallBox extends MazeBox {
    public static final char Label = 'W';
    public static final Color color = new Color(54, 65, 86);

    public WallBox(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isWall() {
        return true;
    }

    public Color getColor() {
        return color;
    }
}
