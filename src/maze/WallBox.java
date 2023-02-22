package maze;

import java.awt.*;

public class WallBox extends MazeBox {
    public static final char Label = 'W';//Ceci est uniquement pour permettre le changeBox plus clair, son utilisation respecte le principe de l'O.O.
    public static final Color color = new Color(54, 65, 86);

    public WallBox(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isWall() {//On réécrit uniquement la méthode qui doit renvoyer true
        return true;
    }

    public Color getColor() {
        return color;
    }
}
