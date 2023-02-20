package maze;

import java.awt.*;

public class DepartureBox extends EmptyBox {
    public static final char Label = 'D';
    public static final Color color = new Color(71, 229, 188);

    public DepartureBox(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isDeparture() {
        return true;
    }

    public Color getColor() {
        return color;
    }

}

