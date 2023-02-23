package maze;

import graph.*;

import java.io.*;
import java.util.ArrayList;

public class Maze implements Graph {
    private MazeBox[][] maze;
    private MazeBox arrival;
    private MazeBox departure;
    private VertexPath solvePath = new DijsktraPath();
    private MazeBox oldArrival;
    private MazeBox oldDeparture;

    public Maze(int height, int width) {
        maze = new MazeBox[height][width];
        this.initBlank();
    }

    public Maze() {
        maze = new MazeBox[10][10];
        this.initBlank();
    }

    /**
     * @return return the width of the maze (number of column)
     */
    public int getWidth() {
        return maze[0].length;

    }

    /**
     * @return return the length of the maze (number of line)
     */
    public int getHeight() {
        return maze.length;
    }

    /**
     * Get a MazeBox of the maze
     *
     * @param line   coordinate of the Mazebox wanted
     * @param column coordinate of the Mazebox wanted
     * @return the MazeBox wanted
     */
    public MazeBox getMazeBox(int line, int column) {
        return maze[line][column];
    }

    /**
     * Initialize the maze with all the mazebox empty, with the exception of one
     * departure and one arrival at one corner each
     */
    public void initBlank() {
        pathChanged();
        for (int i = 0; i < getHeight(); i++) {
            for (int u = 0; u < getWidth(); u++) {
                maze[i][u] = new EmptyBox(i, u);
            }
        }
        oldArrival = null;
        oldDeparture = null;
        maze[0][0] = new DepartureBox(0, 0);
        maze[getHeight() - 1][getWidth() - 1] = new ArrivalBox(getHeight() - 1, getWidth() - 1);
    }

    /**
     * randomize the maze, except for the departure and the arrival
     */
    public void randomize() {
        pathChanged();
        for (int i = 0; i < getHeight(); i++) {//Tous les murs sont des wallbox
            for (int u = 0; u < getWidth(); u++) {
                    maze[i][u] = new WallBox(i, u);
            }
        }

        int rLine = (int) (Math.random() * getHeight());
        int rColumn = (int) (Math.random() * getWidth());
        maze[rLine][rColumn] = new DepartureBox(rLine, rColumn);

        while(maze[rLine][rColumn].isDeparture()) {
            rLine = (int) (Math.random() * getHeight());
            rColumn = (int) (Math.random() * getWidth());
        }

        maze[rLine][rColumn] = new ArrivalBox(rLine, rColumn);

        int even_odd = (int) (Math.random() * 2) + 3;// 3 ou 4, ça permet de faire varier le cadriage des murs qui
        // restes dans le labyrinthe
        int count = 0;
        solvePath = new DijsktraPath(true);
        while (!solvePath.isPath()) {
            count++;
            rLine = (int) (Math.random() * getHeight());
            rColumn = (int) (Math.random() * getWidth());

            MazeBox box = new EmptyBox(rLine, rColumn);
            if (rLine % even_odd != 0 || rColumn % even_odd != 0) {
                setBox(box);
                ArrayList<Vertex> voisin = getSuccessors(box, true);
                int r = (int) (Math.random() * voisin.size());
                MazeBox leVoisin = (MazeBox) voisin.get(r);
                MazeBox leVoisinDuVoisin = (MazeBox) getSuccessors(leVoisin, true).get(0);
                if (leVoisin.getLine() % even_odd != 0 || leVoisin.getColumn() % even_odd != 0) {
                    changeBox(leVoisin, EmptyBox.Label);
                }
                if (leVoisinDuVoisin.getLine() % even_odd != 0 || leVoisinDuVoisin.getColumn() % even_odd != 0) {
                    changeBox(leVoisinDuVoisin, EmptyBox.Label);
                }
            }
            try { //Evite la division par 0
                if (count % (getWidth() * getHeight() / 100) == 0) {// Permet de ne pas faire le dijkstra à chaque fois
                    dijkstra();
                }
            } catch(ArithmeticException e) {
                dijkstra();
            }
        }
    }

    /**
     * Initialize the maze from a text file
     *
     * @param filePath the path of the file from the source folder of the project
     * @throws MazeReadingException all the exception that can generate a wrong type
     *                              of file
     */
    public final void initFromTextFile(String filePath) throws MazeReadingException {
        int fileHeight;
        int fileWidth;
        pathChanged();
        try {
            // Changement de la taille du labyrinthe en fonction du fichier
            FileReader fr = new FileReader(filePath);
            BufferedReader br_test = new BufferedReader(fr);
            String line_test = br_test.readLine();
            fileWidth = line_test.length();
            fileHeight = 0;
            while (line_test != null) {
                fileHeight++;
                line_test = br_test.readLine();
            }
            br_test.close();

            this.maze = new MazeBox[fileHeight][fileWidth];
            initBlank();
            fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            for (int i = 0; i < getHeight(); i++) {
                String line = br.readLine();
                for (int u = 0; u < getWidth(); u++) {

                    switch (line.charAt(u)) {// Création de chaque cellule du labyrinthe en fonction du fichier.
                        case EmptyBox.Label:
                            maze[i][u] = new EmptyBox(i, u);
                            break;
                        case WallBox.Label:
                            maze[i][u] = new WallBox(i, u);
                            break;
                        case ArrivalBox.Label:
                            maze[i][u] = new ArrivalBox(i, u);
                            break;
                        case DepartureBox.Label:
                            maze[i][u] = new DepartureBox(i, u);
                            break;
                        default:
                            initBlank();
                            throw new MazeReadingException(filePath, u, "Unexpected value: " + line.charAt(u));
                    }
                }
            }
            br.close();

            // Check maze
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
                throw new MazeReadingException(filePath, getHeight(), "Un départ est requis");
            }
            if (countArrival == 0) {
                maze[0][0] = new ArrivalBox(getHeight() - 1, getWidth() - 1);
                throw new MazeReadingException(filePath, getHeight(), "Une arrivée est requise");
            }

            if (countArrival > 1) {
                initBlank();
                throw new MazeReadingException(filePath, lineError,
                        countArrival + " arrivées, une seule arrivée est requise");
            }
            if (countDeparture > 1) {
                initBlank();
                throw new MazeReadingException(filePath, lineError,
                        countDeparture + " départs, un seul départ est requis");
            }

        } catch (IndexOutOfBoundsException e) {
            initBlank();
            throw new MazeReadingException(filePath, 0, "Toutes les lignes doivent avoir la même longueur");

        } catch (IOException e) {
            initBlank();
            throw new MazeReadingException(filePath, 0, e.getMessage());
        }

    }

    public final void saveToTextFile(String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
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
     * @param vertex
     * @return all the mazebox who are not wall neigbhoor of a mazebox
     */
    public ArrayList<Vertex> getSuccessors(Vertex vertex) {

        return getSuccessors(vertex, false);
    }

    /**
     * @param vertex   the vertex
     * @param alsoWall if true, the wall are also returned
     * @return all the mazebox who are neigbhoor of a mazebox (also wall if alsoWall is true)
     */
    public ArrayList<Vertex> getSuccessors(Vertex vertex, boolean alsoWall) {
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

        if (line % 2 == 0) {// Ligne pair
            if (line != 0 && column != 0) {
                successors.add(maze[line - 1][column - 1]);
            }
            if (line != maze.length - 1 && column != 0) {
                successors.add(maze[line + 1][column - 1]);
            }
        } else {// ligne impair
            if (column != maze[0].length - 1) {
                successors.add(maze[line - 1][column + 1]);
            }
            if (line != maze.length - 1 && column != maze[0].length - 1) {
                successors.add(maze[line + 1][column + 1]);
            }
        }

        // Suppression des murs en tant que successeurs
        if (!alsoWall) {
            int i = 0;
            while (i < successors.size()) {
                MazeBox succ = (MazeBox) successors.get(i);
                if (succ.isWall()) {
                    successors.remove(i);
                } else {
                    i++;
                }
            }
        }

        return successors;
    }

    /**
     * @param src a mazeBox
     * @param dst a mazebox
     * @return the distance between 2 consecutive mazebox
     */
    public int getDistance(Vertex src, Vertex dst) {
        return 1;
    }

    /**
     * Get the arrival mazebox
     *
     * @return the arrival mazebox
     */
    private MazeBox getArrival() {
        arrival = null;
        for (int i = 0; i < maze.length; i++) {
            for (int u = 0; u < maze[0].length; u++) {
                if (maze[i][u].isArrival())
                    arrival = maze[i][u];
            }
        }

        return arrival;
    }

    /**
     * Get the departure mazebox
     *
     * @return the departure mazebox
     */
    private MazeBox getDeparture() {
        departure = null;
        for (int i = 0; i < maze.length; i++) {
            for (int u = 0; u < maze[0].length; u++) {
                if (maze[i][u].isDeparture())
                    departure = maze[i][u];
            }
        }

        return departure;
    }


    /**
     * set a mazebox to something else, it also delete the old arrival or departure
     *
     * @param box the mazebox to change
     */
    private void setBox(MazeBox box) {
        int line = box.getLine();
        int column = box.getColumn();
        MazeBox boxHere = getMazeBox(line, column);
        arrival = getArrival();
        departure = getDeparture();

        if (!boxHere.isArrival() && !boxHere.isDeparture()) {
            if (box.isArrival()) {
                int aLine = arrival.getLine();
                int aColumn = arrival.getColumn();
                if (oldArrival != null) {
                    maze[aLine][aColumn] = oldArrival;
                } else {
                    maze[aLine][aColumn] = new EmptyBox(aLine, aColumn);
                }
                oldArrival = getMazeBox(line, column);
            }

            if (box.isDeparture()) {
                int dLine = departure.getLine();
                int dColumn = departure.getColumn();
                if (oldDeparture != null) {
                    maze[dLine][dColumn] = oldDeparture;
                } else {
                    maze[dLine][dColumn] = new EmptyBox(dLine, dColumn);
                }
                oldDeparture = getMazeBox(line, column);
            }

            maze[line][column] = box;
        }
    }

    /**
     * Allow to change the type of specified mazebox
     *
     * @param box
     * @param choice, a string who can be D for departure, A for arrival, W for wall
     *                or E for empty, you can use the constant of the MazeBox classes (Label)
     */
    public boolean changeBox(MazeBox box, char choice) {
        boolean changed = false;
        boolean pathChanged = false;

        if (choice != WallBox.Label || solvePath.isIncluded(box)) {
            pathChanged = true;
        }

        if (!(getMazeBox(box.getLine(), box.getColumn()).isArrival()
                || getMazeBox(box.getLine(), box.getColumn()).isDeparture())) {

            switch (choice) {
                case ArrivalBox.Label:
                    if (!box.isArrival()) {
                        setBox(new ArrivalBox(box.getLine(), box.getColumn()));
                        changed = true;
                    }
                    break;
                case DepartureBox.Label:
                    if (!box.isDeparture()) {
                        setBox(new DepartureBox(box.getLine(), box.getColumn()));
                        changed = true;
                    }
                    break;
                case EmptyBox.Label:
                    if (!box.isEmpty()) {
                        setBox(new EmptyBox(box.getLine(), box.getColumn()));
                        changed = true;
                    }
                    break;
                case WallBox.Label:
                    if (!box.isWall()) {
                        setBox(new WallBox(box.getLine(), box.getColumn()));
                        changed = true;
                    }
                    break;
            }
        }
        if(pathChanged && changed)
            pathChanged();
        return changed;
    }

    /**
     * when a method is used that edit the maze, it reset the solution path
     */
    private void pathChanged() {
        solvePath = new DijsktraPath(false);
    }

    /**
     * Calculate the shortest path to solve the maze, register it as an attribute,
     * et also return it
     *
     * @return a VertexPath that correspond to the shortest path to solve the maze
     */
    public VertexPath dijkstra() {
        getDeparture();
        getArrival();
        this.solvePath = Dijkstra.dijkstra(this, departure, arrival);//Le maze n'a pas besoin de connaitre le search patern de Dijsktra
        return solvePath;
    }

    /**
     * Give the shortest path the maze know to solve itself
     *
     * @return a VertexPath who correspond of the shortest path to solve the maze
     */
    public VertexPath getPath() {
        return solvePath;
    }

    @Override
    public String toString() {//Cette méthode est utilisée pour l'affichage du labyrinthe dans la console
        String mazeString = "";
        for (int i = 0; i < getHeight(); i++) {
            if (i % 2 != 0)
                mazeString = mazeString.concat(" ");
            for (int u = 0; u < getWidth(); u++) {
                String color;
                if (maze[i][u].isDeparture())
                    color = "\u001B[34m";
                else if (maze[i][u].isArrival())
                    color = "\u001B[36m";
                else if (maze[i][u].isWall())
                    color = "\u001B[30m";
                else
                    color = "\u001B[0m";
                mazeString = mazeString.concat(color + "" + maze[i][u].toString() + " ");
            }
            mazeString = mazeString.concat("\n");
        }
        mazeString = mazeString.concat("\u001B[0m");
        return mazeString;
    }
}
