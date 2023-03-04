package ui;

import graph.VertexPath;
import maze.EmptyBox;
import maze.Maze;
import maze.MazeBox;
import maze.WallBox;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private final Maze maze;
    private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();
    private boolean mazeChanged = true;
    private MazeBox boxHover = null;
    private char select = WallBox.Label;
    private Boolean autoDijkstra = false;
    private Boolean saved = true;
    private MazeBox boxSelected = null;
    private long chronoStart;

    public Model(Maze maze) {
        this.maze = maze;
        stateChanged();
    }

    /**
     * get the type of element to draw
     *
     * @return the selected button
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
            saved = false;
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
            saved = false;
        }
    }

    /**
     * get the distance to solve the maze
     *
     * @return the distance
     */
    public int getDistance() {
        int distance = maze.getPath().getDistance();
        if ((distance == -1) && autoDijkstra) {//S'il ne connait pas le chemin, mais qu'autoDijkstra est activé, il le calcule
            VertexPath temp = maze.dijkstra();
            distance = temp.getDistance();
        }
        return distance;
    }

    public void randomize() {
        maze.randomize();
        boxHover = null;
        stateChanged();
        saved = false;
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
     * @return changed, return true if the box hover has been changed
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

    /**
     * Add a listener to the model
     *
     * @param listener the listener to add
     */
    public void addObserver(ChangeListener listener) {
        listeners.add(listener);
    }

    public Boolean isSave() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }

    public MazeBox getBoxSelected() {
        return boxSelected;
    }

    public void setBoxSelected(MazeBox boxSelected) {
        this.boxSelected = boxSelected;
        stateChanged();
    }

    public void setChronoStart(long chronoStart) {
        this.chronoStart = chronoStart;
    }

    public long getChronoStart() {
        return chronoStart;
    }
}
