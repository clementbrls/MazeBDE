package ui;
import Graph.*;
import Maze.*;

import java.awt.*;


public class DrawMaze {

    public static final Color colorArrival = new Color(254, 147, 140);
    public static final Color colorDeparture = new Color(71, 229, 188);
    public static final Color colorWall = new Color(54, 65, 86);
    public static final Color colorEmpty = new Color(236, 235, 243);
    public static final Color colorDefault = new Color(241, 243, 244);
    private final Maze maze;
    private String select = "W";
    private Boolean autoDijkstra = true;
    private FrameUI frame;

    private Boolean infoPanelVisibility =true;

    public DrawMaze(FrameUI frame,Maze maze) {
        this.frame=frame;
        this.maze = maze;
    }


    public void drawMaze(Graphics g) {
        Color color;
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int u = 0; u < maze.getWidth(); u++) {
                if (maze.getMazeBox(i, u).isWall()) color = colorWall;
                else if (maze.getMazeBox(i, u).isDeparture()) color = colorDeparture;
                else if (maze.getMazeBox(i, u).isArrival()) color = colorArrival;
                else color = colorEmpty;
                Polygon p = Geometry.mazeBoxToHexa(maze.getMazeBox(i, u));
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//Anti-aliasing
                g.setColor(color);
                g.drawPolygon(p);
                g.fillPolygon(p);

            }
        }
    }

    public void drawPath(Graphics g) {
        VertexPath path = maze.getPath();
        if (autoDijkstra && path.getDistance()==-1) {
            path = maze.dijkstra();
        }


        for (int i = 0; i < path.size(); i++) {
            if (i + 1 < path.size()) {
                MazeBox box = (MazeBox) path.get(i);
                MazeBox oldBox = (MazeBox) path.get(i + 1);
                Graphics2D g2 = (Graphics2D) g;
                //g.setColor(new Color(7, 160, 195));
                g.setColor(new Color(0, 168, 224));
                g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));//Line more thick with rounded corners
                g.drawLine(Geometry.mazeBoxToCoord(box).x, Geometry.mazeBoxToCoord(box).y, Geometry.mazeBoxToCoord(oldBox).x, Geometry.mazeBoxToCoord(oldBox).y);
            }
        }

        frame.repaint();
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String selection) {
        select = selection;
    }

    public void changeBox(MazeBox box) {
        maze.changeBox(box, select);
    }

    public void changeBox(MazeBox box, Boolean setEmpty) {
        if (setEmpty) {
            maze.changeBox(box, "E");
        } else {
            maze.changeBox(box, select);
        }
    }

    public int getDistance() {
        return maze.getPath().getDistance();
    }

    public Maze getMaze() {
        return maze;
    }

    public Boolean getAutoDijkstra() {
        return autoDijkstra;
    }

    public void setAutoDijkstra(Boolean autoDijkstra) {
        this.autoDijkstra = autoDijkstra;
    }

    public Boolean getInfoPanelVisibility() {
        return infoPanelVisibility;
    }

    public void setInfoPanelVisibility(Boolean infoPanelVisibility) {
        this.infoPanelVisibility = infoPanelVisibility;
    }
}
