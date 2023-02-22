package ui;

import graph.*;
import maze.*;


import java.awt.*;
import java.awt.geom.*;

//----- Vue -----

/**
 * Class that draw the maze
 */
public class DrawMaze {
    public static final float sizeDefault = 22; //largeur d'un hexagone
    private final Model model; //model
    public float border = 3; //distance entre 2 hexagones (ici 3, mais sera recalculé après)
    private float size = sizeDefault; //taille d'un hexagone
    float offsetOdd = size + border / 2;//L'écart quand la ligne est impaire
    float x_start = offsetOdd * 2; //x position du centre du premier hexagone
    float y_start = size * 2; //y position du centre du premier hexagone
    private Graphics2D g2;

    public DrawMaze(Model model) {
        this.model = model;//On initialise le model
    }

    /**
     * Set the info of the view, to correctly draw the maze
     *
     * @param g      Graphics //Graphics of the panel
     * @param width  float of the panel
     * @param height float of the panel
     */
    public void setInfo(Graphics g, int width, int height) {
        this.g2 = (Graphics2D) g;
        calcSize(model.getMaze(), (float) width, (float) height);
    }

    /**
     * draw the maze
     */
    public void drawMaze() {
        Maze maze = model.getMaze();//récup le maze depuis le model
        Color color;

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int u = 0; u < maze.getWidth(); u++) {
                MazeBox box = maze.getMazeBox(i, u);//2 boucles for imbriquer qui permette de récupérer chaque case
                color = box.getColor();//On a choisi d'utiliser la couleur que la case propose (on aurait pu faire different)
                Path2D hexa = mazeBoxToHexa(box);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Anti-aliasing
                g2.setColor(color);
                g2.fill(hexa);//Affiche la case
            }
        }
    }

    /**
     * draw the path that Dijkstra found
     */
    public void drawPath() {
        VertexPath path = model.getMaze().getPath();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Anti-aliasing

        if (model.getAutoDijkstra() && !path.isPath()) {
            path = model.getMaze().dijkstra();//Si le mode auto est activé et que le chemin n'est pas trouvé, on le cherche
        }
        if (path.isPath())
            for (int i = 0; i <= path.getDistance(); i++) {
                if (i + 1 <= path.getDistance()) {
                    MazeBox box = (MazeBox) path.get(i);
                    MazeBox oldBox = (MazeBox) path.get(i + 1);//On prends les cases qui sont relier 2 à 2
                    Point2D pBox = mazeBoxToCoord(box);//On calcule les coordonnées des cases
                    Point2D pOldBox = mazeBoxToCoord(oldBox);
                    g2.setColor(new Color(27, 169, 222));
                    float stroke = border * 2;
                    g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Ligne plus épaisse et bord arrondi
                    g2.draw(new Line2D.Double(pBox, pOldBox));
                }
            }
    }

    /**
     * draw the hover box
     */
    public void drawHover() {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Anti-aliasing

        MazeBox boxHover = model.getBoxHover();
        if (boxHover != null) {
            g2.setColor(boxHover.getColor().darker());//La case que l'on a sélectionnée devient plus foncé. On continue d'utiliser la couleur que mazebox propose
            Path2D hexa = mazeBoxToHexa(boxHover);
            g2.fill(hexa);

        }
    }



    //------------------------------------GeometryFactory------------------------------------//
    //Cette partie possède toutes les méthodes mathématiques qui permettent de calculer les coordonnées des cases
    //Historiquement, c'était une classe séparée, qui possédait uniquement des méthodes statiques

    /**
     * This part is useful to create the maze that is responsive to the size of the window.
     * It calculates the size of the hexagons and the distance between them
     *
     * @param maze   The maze
     * @param width  The width of the window
     * @param height The height of the window
     */
    private void calcSize(Maze maze, float width, float height) {
        //On calcule la taille que devrait prendre un hexagone pour que le labyrinthe soit responsive en hauteur et largeur
        float sizeWidth = (width - border * maze.getWidth()) / (2 + 2 * maze.getWidth() + 1);
        float sizeHeight = (float) ((height - (maze.getHeight() + 1) * Math.cos(Math.PI / 6) * border) / ((maze.getHeight() + 1) * 2 * Math.cos(Math.PI / 6) + 1));

        //On prend la plus petite des deux valeurs
        if (sizeHeight > sizeWidth) {
            offsetOdd = size + border / 2;
            x_start = size * 2;
            float sizetheo = (float) (sizeWidth + (maze.getHeight()) * ((2 * sizeWidth + border * Math.cos(Math.PI / 6))));//On calcule la largeur que le labyrinthe va faire
            y_start = (height / 2) - (sizetheo / 2) + sizeWidth * 2.5f; //on calcule la position en y du premier hexagone pour que le labyrinthe soit centré en hauteur
            size = sizeWidth;
        } else {
            offsetOdd = size + border / 2;
            y_start = size * 2;
            float sizetheo = sizeHeight * 2 + (2 * sizeHeight + border) * maze.getWidth() + sizeHeight;//On calcule la hauteur que le labyrinthe va faire
            x_start = (width / 2) - (sizetheo / 2) + sizeHeight * 2; //on calcule la position en x du premier hexagone pour que le labyrinthe soit centré en largeur
            size = sizeHeight;
        }
        //border = size * 0.15f;//Calcul de la distance entre les hexagones
    }


    /**
     * This method create a Path2D that represent a hexagon with the center in the coordinates x and y
     *
     * @param x the x coordinate of the center
     * @param y the y coordinate of the center
     * @return the Path2D that represent the hexagon
     */
    private Path2D createHexa(float x, float y) {
        float arc = (float) (size / Math.cos(Math.PI / 6));//Calcul de la longueur d'un arc (entre centre de l'hexagone et un sommet)
        Path2D hexa = new Path2D.Float();
        hexa.moveTo(x, y);
        for (int i = 0; i <= 6; i++) {//On prends les 6 points qui forment un hexagone
            float nextX = (float) (Math.cos((Math.PI / 3) * i + Math.PI / 6) * arc + x);
            float nextY = (float) (Math.sin((Math.PI / 3) * i + Math.PI / 6) * arc + y);
            hexa.lineTo(nextX, nextY);//On trace une ligne jusqu'au nouveau point calculé
        }
        hexa.closePath();//Explicite
        return hexa;
    }


    /**
     * Give a mazebox, and it will give you the coordinates of the center of the hexagon
     * It is NOT a static method, it will create the hexagon with the size et border that drawMaze has at attributes
     *
     * @param box the mazebox
     * @return the coordinates of the center of the hexagon
     */
    private Point2D mazeBoxToCoord(MazeBox box) {
        int line = box.getLine();
        int column = box.getColumn();
        float x;
        float y;

        y = (float) (y_start + line * ((2 * size + border) * Math.cos(Math.PI / 6)));

        if (line % 2 == 0) x = x_start + 2 * size * column + (column * border);//Si la ligne est paire
        else x = x_start + 2 * size * column + (column * border) + offsetOdd;


        return new Point2D.Float(x, y);
    }


    /**
     * give a mazebox, and it will give you the coordinates of the 6 summit of the hexagon
     * it allows you to do 2 things at once (calculating the center and the summit)
     *
     * @param box the mazebox
     * @return the coordinates of the 6 summit of the hexagon
     */
    public Path2D mazeBoxToHexa(MazeBox box) {
        Point2D a = mazeBoxToCoord(box);
        return createHexa((float) a.getX(), (float) a.getY());
    }

    /**
     * Give the coordinates of the mouse, and it will give you the mazebox where the mouse is
     *
     * @param maze the maze
     * @param x    the x coordinate of the mouse
     * @param y    the y coordinate of the mouse
     * @return the mazebox where the mouse is
     */
    public MazeBox coordToMazeBox(Maze maze, int x, int y) {//cette méthode est publique car elle permet de faire de lien entre la vue et le controlleur.
        if (maze.getHeight() * maze.getWidth() < 1000) {
            return coordToMazeBox(maze, x, y, false);
        } else {
            return coordToMazeBox(maze, x, y, true);
        }
    }

    public MazeBox coordToMazeBox(Maze maze, int x, int y, boolean fast) {
        if (fast) {//Cette méthode termine de façon mathématique la case la plus proche de la souris
            int line = (int) Math.round((y - y_start) / ((2 * size + border) * Math.cos(Math.PI / 6)));
            int column;
            if (line % 2 == 0)
                column = Math.round((x - x_start) / (2 * size + border));
            else
                column = Math.round((x - x_start - offsetOdd) / (2 * size + border));

            if (line < 0 || line >= maze.getHeight() || column < 0 || column >= maze.getWidth())//Si jamais on détecte une case impossible, on renvoie null
                return null;
            else
                return maze.getMazeBox(line, column);
        } else {
            //On parcourt toutes les cases du labyrinthe et on regarde si la souris est dans l'hexagone associer à la case
            for (int i = 0; i < maze.getHeight(); i++) {//C'est une méthode lente, mais elle est plus précise
                for (int u = 0; u < maze.getWidth(); u++) {
                    MazeBox boxTest = maze.getMazeBox(i, u);
                    if (mazeBoxToHexa(boxTest).contains(x, y)) {
                        return boxTest;
                    }
                }
            }
            return null;//Si on a pas trouvé de case, on renvoie null
        }
    }
}
