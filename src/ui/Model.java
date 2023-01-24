package ui;
import Maze.*;

public class Model {

    private final Maze maze;
    private boolean mazeChanged=true;
    private MazeBox boxHover=null;
    private MazeBox oldBoxHover=null;
    private char select = WallBox.Label;
    private Boolean autoDijkstra = true;

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
    }

    /**
     * change the box clicked
     * @param box the box to change
     */
    public void changeBox(MazeBox box) {
        mazeChanged=maze.changeBox(box, select);
    }

    public void changeBox(MazeBox box, Boolean setEmpty) {
        if (setEmpty) {
            mazeChanged=maze.changeBox(box, EmptyBox.Label);
        } else {
            mazeChanged=maze.changeBox(box, select);
        }
    }

    /**
     * get the distance to solve the maze
     * @return the distance
     */
    public int getDistance() {
        return maze.getPath().getDistance();
    }

    /**
     * get the maze
     * @return the maze
     */
    public Maze getMaze() {
        return maze;
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
    }


    public void setBoxHover(MazeBox boxHover) {
        if(boxHover!=this.boxHover){
            if(this.boxHover!=null){
                oldBoxHover=this.boxHover;
            }
            this.boxHover = boxHover;
        }
    }


    public MazeBox getBoxHover() {
        return boxHover;
    }

    public boolean isMazeChanged() {
        return mazeChanged;
    }


    public MazeBox getOldBoxHover() {
        return oldBoxHover;
    }
}
