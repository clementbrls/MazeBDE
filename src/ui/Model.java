package ui;
import Maze.*;

import javax.swing.event.*;
import java.util.*;

public class Model {

    private final Maze maze;
    private boolean mazeChanged=true;
    private MazeBox boxHover=null;
    private char select = WallBox.Label;
    private Boolean autoDijkstra = true;

    private final List<ChangeListener> listeners = new ArrayList<ChangeListener>() ;

    public Model(Maze maze) {
        this.maze = maze;
    }

    /**
     * get the type of element to draw
     * @return
     */
    public char getSelect() {
        return select;
    }

    /**
     * set the type of element to draw
     * @param selection
     */
    public void setSelect(char selection) {
        select = selection;
        stateChanged();
    }

    /**
     * change the box clicked
     * @param box the box to change
     */
    public void changeBox(MazeBox box) {
        mazeChanged=maze.changeBox(box, select);
        if(mazeChanged){
            stateChanged();
        }
    }

    public void changeBox(MazeBox box, Boolean setEmpty) {
        if (setEmpty) {
            mazeChanged=maze.changeBox(box, EmptyBox.Label);
        } else {
            mazeChanged=maze.changeBox(box, select);
        }
        if(mazeChanged){
            stateChanged();
        }
    }

    /**
     * get the distance to solve the maze
     * @return the distance
     */
    public int getDistance() {
        int distance = maze.getPath().getDistance();
        if (distance == -1 && autoDijkstra) {
            maze.dijkstra();
            distance = maze.getPath().getDistance();
        }
        return distance ;
    }

    /**
     * get the maze
     * @return the maze
     */
    public Maze getMaze() {
        return maze;
    }

    public void doDijsktra(){
        maze.dijkstra();
        stateChanged();
    }

    /**
     * get the autoDijkstra parameter
     * @return the autoDijkstra parameter
     */
    public Boolean getAutoDijkstra() {
        return autoDijkstra;
    }

    /**
     * set the autoDijkstra parameter
     * @param autoDijkstra can be true or false
     */
    public void setAutoDijkstra(Boolean autoDijkstra) {
        this.autoDijkstra = autoDijkstra;
        stateChanged();
    }


    public boolean setBoxHover(MazeBox boxHover) {
        boolean changed = false;
        if(boxHover!=this.boxHover){
            changed=true;
            this.boxHover = boxHover;
            stateChanged();
        }
        return changed;
    }


    public MazeBox getBoxHover() {
        return boxHover;
    }

    public void stateChanged() {
        ChangeEvent evt = new ChangeEvent(this);

        for (ChangeListener listener : listeners) {
            listener.stateChanged(evt);
        }
    }
    public void addObserver(ChangeListener listener) {
        listeners.add(listener) ;
    }
}
