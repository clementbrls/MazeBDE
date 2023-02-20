package ui;

import maze.*;

import javax.swing.event.*;
import java.util.*;

public class Model {

    private final Maze maze;
    private boolean mazeChanged = true;
    private MazeBox boxHover = null;
    private char select = WallBox.Label;
    private Boolean autoDijkstra = true;

    private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();

    public Model(Maze maze) {
        this.maze = maze;
        stateChanged();
    }

    /**
     * get the type of element to draw
     *
     * @return
     */
    public char getSelect() {
        return select;
    }

    /**
     * set the type of element to draw
     *
     * @param selection the type of element to draw
     */
    public void setSelect(char selection) {
        select = selection;
        stateChanged();
    }

    /**
     * change the box clicked
     *
     * @param box the box to change
     */
    public void changeBox(MazeBox box) {
        mazeChanged = maze.changeBox(box, select);
        if (mazeChanged) {
            stateChanged();
        }
    }

    /**
     * change the box clicked
     *
     * @param box      the box to change
     * @param setEmpty if true, the box will be empty
     */
    public void changeBox(MazeBox box, Boolean setEmpty) {
        if (setEmpty) {
            mazeChanged = maze.changeBox(box, EmptyBox.Label);
        } else {
            mazeChanged = maze.changeBox(box, select);
        }
        if (mazeChanged) {
            stateChanged();
        }
    }

    /**
     * get the distance to solve the maze
     *
     * @return the distance
     */
    public int getDistance() {
        int distance = maze.getPath().getDistance();
        if (distance == -1 && autoDijkstra) {//S'il ne connait pas le chemin, mais que autoDijsktra est activé, il le calcule
            maze.dijkstra();
            distance = maze.getPath().getDistance();
        }
        return distance;
    }

    /**
     * get the maze
     *
     * @return the maze
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * Execute the dijkstra algorithm to find the shortest path
     */
    public void doDijsktra() {
        maze.dijkstra();
        stateChanged();
    }

    /**
     * get the autoDijkstra parameter
     *
     * @return the autoDijkstra parameter
     */
    public Boolean getAutoDijkstra() {
        return autoDijkstra;
    }

    /**
     * set the autoDijkstra parameter
     *
     * @param autoDijkstra can be true or false
     */
    public void setAutoDijkstra(Boolean autoDijkstra) {
        this.autoDijkstra = autoDijkstra;
        stateChanged();
    }


    /**
     * set the box hovered
     *
     * @param boxHover the box hovered
     */
    public boolean setBoxHover(MazeBox boxHover) {
        boolean changed = false;
        if (boxHover != this.boxHover) {
            changed = true;
            this.boxHover = boxHover;
            stateChanged();
        }
        return changed;
    }

    /**
     * get the box hovered
     *
     * @return the box hovered
     */
    public MazeBox getBoxHover() {
        return boxHover;
    }

    /**
     * Signal that the model has changed
     */
    public void stateChanged() {
        ChangeEvent evt = new ChangeEvent(this);

        for (ChangeListener listener : listeners) {
            listener.stateChanged(evt);
        }
    }

    public void addObserver(ChangeListener listener) {
        listeners.add(listener);
    }
}
