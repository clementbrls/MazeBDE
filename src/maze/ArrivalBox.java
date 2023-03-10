package maze;

import java.awt.*;

public class ArrivalBox extends EmptyBox {
    public static final char Label = 'A';//Ceci est uniquement pour permettre le changeBox plus clair, son utilisation respecte le principe de l'O.O.
    public static final Color color = new Color(254, 147, 140);


    public ArrivalBox(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isArrival() {
        return true;
    }

    public Color getColor() {
        return color;
    }

}
