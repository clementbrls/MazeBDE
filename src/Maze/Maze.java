package Maze;

import Graph.*;

import java.io.*;
import java.util.ArrayList;

public class Maze implements Graph {
    private MazeBox[][] maze;
    private MazeBox arrival;
    private MazeBox departure;
    private VertexPath solveMaze=new VertexPath();


    public Maze(int height, int width) {
        maze = new MazeBox[height][width];
        this.initBlank();
    }

    /**
     *  @return return the width of the maze (number of column)
     */
    public int getWidth() {
        return maze[0].length;

    }

    /**
     *  @return return the length of the maze (number of line)
     */
    public int getHeight() {
        return maze.length;
    }

    /**
     * Get a MazeBox of the maze
     * @param line coordinate of the Mazebox wanted
     * @param column coordinate of the Mazebox wanted
     * @return the MazeBox wanted
     */
    public MazeBox getMazeBox(int line, int column) {
        return maze[line][column];
    }

    /**
     * set a mazebox to something else, it also delete the old arrival or departure
     * @param box the mazebox to change
     */
    private void setBox(MazeBox box) {
        int line = box.getLine();
        int column = box.getColumn();
        if (box.isArrival()) {
            int aLine = getArrival().getLine();
            int aColumn = getArrival().getColumn();
            maze[aLine][aColumn] = new EmptyBox(aLine, aColumn);
        }

        if (box.isDeparture()) {
            int dLine = getDeparture().getLine();
            int dColumn = getDeparture().getColumn();
            maze[dLine][dColumn] = new EmptyBox(dLine, dColumn);
        }

        maze[line][column] = box;

    }

    /**
     * Initialize the maze with all the mazebox empty, with the exception of one departure and one arrival at one corner each
     */
    public void initBlank() {
        pathChanged();
        for (int i = 0; i < getHeight(); i++) {
            for (int u = 0; u < getWidth(); u++) {
                setBox(new EmptyBox(i, u));
            }
        }
        maze[0][0] = new DepartureBox(0, 0);
        maze[getHeight() - 1][getWidth() - 1] = new ArrivalBox(getHeight() - 1, getWidth() - 1);


    }

    /**
     * Initialize the maze from a text file
     * @param fileName the path of the file from the source folder of the project
     * @throws MazeReadingException all the exception that can generate a wrong type of file
     */
    public final void initFromTextFile(String fileName) throws MazeReadingException {
        int fileHeight;
        int fileWidth;
        pathChanged();
        try {
            //Changement de la taille du labyrinthe en fonction du fichier
            FileReader fr = new FileReader(fileName);
            BufferedReader br_test = new BufferedReader(fr);
            String line_test = br_test.readLine();
            fileWidth = line_test.length();
            fileHeight = 0;
            while (line_test != null) {
                fileHeight++;
                line_test = br_test.readLine();
            }

            this.maze = new MazeBox[fileHeight][fileWidth];
            initBlank();
            fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            for (int i = 0; i < getHeight(); i++) {
                String line = br.readLine();
                for (int u = 0; u < getWidth(); u++) {

                    maze[i][u] = switch (line.charAt(u)) {//Création de chaque cellule du labyrinthe en fonction du fichier.
                        case EmptyBox.Label:
                            yield new EmptyBox(i, u);
                        case WallBox.Label:
                            yield new WallBox(i, u);
                        case ArrivalBox.Label:
                            yield new ArrivalBox(i, u);
                        case DepartureBox.Label:
                            yield new DepartureBox(i, u);
                        default:
                            initBlank();
                            throw new MazeReadingException(fileName, u, "Unexpected value: " + line.charAt(u));
                    };
                }
            }
            //Check maze
            int countArrival = 0;
            int countDeparture = 0;
            int lineError = -1;
            for (int i = 0; i < maze.length; i++) {
                for (int u = 0; u < maze[0].length; u++) {

                    if (maze[i][u].isArrival()) {
                        countArrival++;
                        lineError = i;
                    }
                    if (maze[i][u].isDeparture()) {
                        countDeparture++;
                        lineError = i;
                    }

                }
            }
            if (countDeparture == 0) {
                maze[0][0] = new DepartureBox(0, 0);
                throw new MazeReadingException(fileName,getHeight(),"One departure is required");
            }
            if (countArrival == 0) {
                maze[0][0] = new ArrivalBox(getHeight() - 1, getWidth() - 1);
                throw new MazeReadingException(fileName,getHeight(),"One arrival is required");
            }

            if (countArrival > 1) {
                initBlank();
                throw new MazeReadingException(fileName, lineError, countArrival+ " arrival is too much, only one arrival is required");
            }
            if (countDeparture > 1) {
                initBlank();
                throw new MazeReadingException(fileName, lineError, countDeparture+" departure is too much, only one departure is required");
            }


        } catch (IndexOutOfBoundsException e) {
            initBlank();
            throw new MazeReadingException(fileName, 0, "All line must be the same length");

        } catch (IOException e) {
            initBlank();
            throw new MazeReadingException(fileName, 0, e.getMessage());
        }


    }

    public final void saveToTextFile(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            PrintWriter pw = new PrintWriter(fos);

            for (int i = 0; i < getHeight(); i++) {
                for (int u = 0; u < getWidth(); u++) {

                    if (getMazeBox(i, u).isWall())
                        pw.print(WallBox.Label);
                    else if (maze[i][u].isArrival())
                        pw.print(ArrivalBox.Label);
                    else if (maze[i][u].isDeparture())
                        pw.print(DepartureBox.Label);
                    else
                        pw.print(EmptyBox.Label);
                }
                pw.println();
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Vertex> getAllVertexes() {
        ArrayList<Vertex> allVertex = new ArrayList<Vertex>();
        for (int i = 0; i < maze.length; i++) {
            for (int u = 0; u < maze[0].length; u++) {
                if (maze[i][u].isEmpty()) {
                    allVertex.add(maze[i][u]);
                }
            }
        }
        return allVertex;
    }

    /**
     *
     * @param vertex
     * @return all the mazebox who are not wall neigbhoor of a mazebox
     */
    public ArrayList<Vertex> getSuccessors(Vertex vertex) {
        MazeBox box = (MazeBox) vertex;
        int line = box.getLine();
        int column = box.getColumn();
        ArrayList<Vertex> successors = new ArrayList<Vertex>();


        if (line != 0) {
            successors.add(maze[line - 1][column]);
        }
        if (line != maze.length - 1) {
            successors.add(maze[line + 1][column]);
        }
        if (column != 0) {
            successors.add(maze[line][column - 1]);
        }
        if (column != maze[0].length - 1) {
            successors.add(maze[line][column + 1]);
        }

        if (line % 2 == 0) {//Ligne pair
            if (line != 0 && column != 0) {
                successors.add(maze[line - 1][column - 1]);
            }
            if (line != maze.length - 1 && column != 0) {
                successors.add(maze[line + 1][column - 1]);
            }
        } else {//ligne impair
            if (line != 0 && column != maze[0].length - 1) {
                successors.add(maze[line - 1][column + 1]);
            }
            if (line != maze.length - 1 && column != maze[0].length - 1) {
                successors.add(maze[line + 1][column + 1]);
            }
        }

        //Suppression des murs en tant que successeurs
        int i = 0;
        while (i < successors.size()) {
            MazeBox succ = (MazeBox) successors.get(i);
            if (succ.isWall()) {
                successors.remove(i);
            } else {
                i++;
            }
        }

        return successors;
    }

    /**
     *
     * @param src a mazeBox
     * @param dst a mazebox
     * @return the distance between 2 consecutive mazebox
     */


    public int getDistance(Vertex src, Vertex dst) {
        return 1;
    }

    /**
     * Get the arrival mazebox
     * @return the arrival mazebox
     */
    private MazeBox getArrival() {
        arrival = null;
        for (int i = 0; i < maze.length; i++) {
            for (int u = 0; u < maze[0].length; u++) {
                if (maze[i][u].isArrival()) arrival = maze[i][u];
            }
        }
        return arrival;
    }

    /**
     * Get the departure mazebox
     * @return the departure mazebox
     */
    private MazeBox getDeparture() {
        departure = null;
        for (int i = 0; i < maze.length; i++) {
            for (int u = 0; u < maze[0].length; u++) {
                if (maze[i][u].isDeparture()) departure = maze[i][u];
            }
        }
        return departure;
    }

    /**
     * Calculate the shortest path to solve the maze, register it as an attribute, et also return it
     * @return a VertexPath that correspond to the shortest path to solve the maze
     */
    public VertexPath dijkstra() {
        getDeparture();
        getArrival();
        this.solveMaze = Dijkstra.dijkstra(this, departure, arrival);
        return solveMaze;
    }

    /**
     * Give the shortest path the maze know to solve itself
     * @return a VertexPath who correspond of the shortest path to solve the maze
     */
    public VertexPath getPath() {
        return solveMaze;
    }

    public void showToConsole() {
        for (int i = 0; i < getHeight(); i++) {
            if (i % 2 != 0) System.out.print(" ");
            for (int u = 0; u < getWidth(); u++) {
                String color;
                if (maze[i][u].isDeparture()) color = "\u001B[34m";
                else if (maze[i][u].isArrival()) color = "\u001B[36m";
                else if (maze[i][u].isWall()) color = "\u001B[30m";
                else color = "\u001B[0m";
                System.out.print(color + "" + maze[i][u].toString() + " ");
            }
            System.out.println();
        }
        System.out.println("\u001B[0m");
    }

    /** Allow to change the type of a specified mazebox
     *
     * @param box
     * @param choice, a string who can be D for departure, A for arrival, W for wall or E for empty
     */
    public boolean changeBox(MazeBox box, char choice) {
        boolean changed = false;
        if(!(choice==WallBox.Label && !solveMaze.isIncluded(box))){
            pathChanged();
        }

        if (!(getMazeBox(box.getLine(), box.getColumn()).isArrival() || getMazeBox(box.getLine(), box.getColumn()).isDeparture())) {


            switch (choice) {
                case ArrivalBox.Label:
                    if(!box.isArrival()) {
                        setBox(new ArrivalBox(box.getLine(), box.getColumn()));
                        changed = true;
                    }
                    break;
                case DepartureBox.Label:
                    if(!box.isDeparture()){
                        setBox(new DepartureBox(box.getLine(), box.getColumn()));
                        changed = true;
                    }
                    break;
                case EmptyBox.Label:
                    setBox(new EmptyBox(box.getLine(), box.getColumn()));
                    break;
                case WallBox.Label:
                    if(!box.isWall()){
                        setBox(new WallBox(box.getLine(), box.getColumn()));
                        changed = true;
                    }
                    break;
            }
        }
        return changed;
    }

    /** when a method is used that edit the maze, it reset the solution path
     */
    private void pathChanged() {
        solveMaze = new VertexPath();
    }
}
