package Maze;

import java.io.*;
import java.util.ArrayList;

public class Maze {
    private MazeBox[][] maze;

    public Maze(){
        maze = new MazeBox[10][10];
    }


    public final void initFromTextFile(String fileName){
        try {
            FileReader fr=new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            for (int i=0; i<10;i++){
                String line=br.readLine();
                for(int u=0; u<10;u++){

                    switch(line.charAt(u)){//Création de chaque cellule du labyrinthe en fonction du fichier.
                        case 'E':
                            maze[i][u]= new EmptyBox(i,u);
                            break;
                        case 'W':
                            maze[i][u]= new WallBox(i,u);
                            break;
                        case 'A':
                            maze[i][u] = new ArrivalBox(i,u);
                            break;
                        case 'D':
                            maze[i][u] = new DepartureBox(i,u);
                            break;
                    }
                }
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
