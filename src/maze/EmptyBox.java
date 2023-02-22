package maze;

import java.awt.*;

public class EmptyBox extends MazeBox {
    public static final char Label = 'E';//Ceci est uniquement pour permettre le changeBox plus clair, son utilisation respecte le principe de l'O.O.
    public static final Color color = new Color(236, 235, 243);

    public EmptyBox(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    public Color getColor() {
        return color;
    }
}
