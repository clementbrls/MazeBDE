package Maze;

import Graph.*;

import java.io.*;
import java.util.ArrayList;

public class Maze implements Graph {
    private MazeBox[][] maze;
    private MazeBox arrival;
    private MazeBox departure;
    private VertexPath solveMaze;


    public Maze(int height, int width) {
        maze = new MazeBox[height][width];
        this.initBlank();
    }

    public int getWidth() {
        return maze[0].length;

    }

    public int getHeight() {
        return maze.length;
    }

    public MazeBox getMazeBox(int line, int column) {
        return maze[line][column];
    }

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

    public void initBlank() {
        isChanged();
        for (int i = 0; i < getHeight(); i++) {
            for (int u = 0; u < getWidth(); u++) {
                setBox(new EmptyBox(i, u));
            }
        }
        maze[0][0] = new DepartureBox(0, 0);
        maze[getHeight() - 1][getWidth() - 1] = new ArrivalBox(getHeight() - 1, getWidth() - 1);


    }

    public final void initFromTextFile(String fileName) throws MazeReadingException {
        int fileHeight;
        int fileWidth;
        isChanged();
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
                        case 'E':
                            yield new EmptyBox(i, u);
                        case 'W':
                            yield new WallBox(i, u);
                        case 'A':
                            yield new ArrivalBox(i, u);
                        case 'D':
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
                        pw.print('W');
                    else if (maze[i][u].isArrival())
                        pw.print('A');
                    else if (maze[i][u].isDeparture())
                        pw.print('D');
                    else
                        pw.print("E");
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

        if (line % 2 == 0) {
            if (line != 0 && column != 0) {
                successors.add(maze[line - 1][column - 1]);
            }
            if (line != maze.length - 1 && column != 0) {
                successors.add(maze[line + 1][column - 1]);
            }
        } else {
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

    public int getDistance(Vertex src, Vertex dst) {
        return 1;
    }

    public MazeBox getArrival() {
        arrival = null;
        for (int i = 0; i < maze.length; i++) {
            for (int u = 0; u < maze[0].length; u++) {
                if (maze[i][u].isArrival()) arrival = maze[i][u];
            }
        }
        return arrival;
    }

    public MazeBox getDeparture() {
        departure = null;
        for (int i = 0; i < maze.length; i++) {
            for (int u = 0; u < maze[0].length; u++) {
                if (maze[i][u].isDeparture()) departure = maze[i][u];
            }
        }
        return departure;
    }

    public VertexPath dijkstra() {
        getDeparture();
        getArrival();

        Graph graph = this;
        this.solveMaze = Dijkstra.dijkstra(graph, departure, arrival);
        return solveMaze;
    }

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
                System.out.print(color + "" + maze[i][u].getLabel() + " ");
            }
            System.out.println();
        }
        System.out.println("\u001B[0m");
    }

    public void changeBox(MazeBox box, String choice) {
        isChanged();
        if (!(getMazeBox(box.getLine(), box.getColumn()).isArrival() || getMazeBox(box.getLine(), box.getColumn()).isDeparture())) {


            switch (choice) {
                case "A":
                    setBox(new ArrivalBox(box.getLine(), box.getColumn()));
                    break;
                case "D":
                    setBox(new DepartureBox(box.getLine(), box.getColumn()));
                    break;
                case "E":
                    setBox(new EmptyBox(box.getLine(), box.getColumn()));
                    break;
                case "W":
                    setBox(new WallBox(box.getLine(), box.getColumn()));
                    break;
            }
        }
    }

    private void isChanged() {
        solveMaze = new VertexPath();
    }

}
