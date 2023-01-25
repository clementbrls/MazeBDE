package ui;

import Graph.*;
import Maze.*;

import java.awt.*;


public class DrawMaze {

    private final FrameUI frame;

    public DrawMaze(FrameUI frame) {
        this.frame = frame;
    }

    /**
     * draw the maze
     *
     * @param g Graphics
     */
    public void drawMaze(Graphics g) {
        //System.out.println("Drawmaze \n \n");
        Maze maze = frame.getModel().getMaze();
        Color color;
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int u = 0; u < maze.getWidth(); u++) {
                MazeBox box = maze.getMazeBox(i, u);

                //Couleur de l'hexagone
                /*
                if (box.isWall()) color = colorWall;
                else if (box.isDeparture()) color = colorDeparture;
                else if (box.isArrival()) color = colorArrival;
                else color = colorEmpty;
                */
                color = box.getColor();
                Polygon p = GeometryFactory.mazeBoxToHexa(box);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Anti-aliasing
                g.setColor(color);
                g.fillPolygon(p);

            }
        }
    }


    /**
     * draw the path
     *
     * @param g Graphics
     */
    public void drawPath(Graphics g) {
        Model model = frame.getModel();
        VertexPath path = model.getMaze().getPath();

        if (model.getAutoDijkstra() && path.getDistance() == -1) {
            path = frame.getModel().getMaze().dijkstra();
        }

        for (int i = 0; i < path.size(); i++) {
            if (i + 1 < path.size()) {
                MazeBox box = (MazeBox) path.get(i);
                MazeBox oldBox = (MazeBox) path.get(i + 1);
                Graphics2D g2 = (Graphics2D) g;
                g.setColor(new Color(0, 168, 224));
                g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Line more thick with rounded corners
                g.drawLine(GeometryFactory.mazeBoxToCoord(box).x, GeometryFactory.mazeBoxToCoord(box).y, GeometryFactory.mazeBoxToCoord(oldBox).x, GeometryFactory.mazeBoxToCoord(oldBox).y);
            }
        }
    }

    public void drawHover(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Anti-aliasing

        MazeBox boxHover = frame.getModel().getBoxHover();
        if (boxHover != null) {
            g2.setColor(boxHover.getColor().darker());
            Polygon p = GeometryFactory.mazeBoxToHexa(boxHover);
            g2.fillPolygon(p);

        }
    }


}
