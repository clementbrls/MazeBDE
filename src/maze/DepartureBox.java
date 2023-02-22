package maze;

import java.awt.*;

public class DepartureBox extends EmptyBox {
    public static final char Label = 'D';//Ceci est uniquement pour permettre le changeBox plus clair, son utilisation respecte le principe de l'O.O.
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

