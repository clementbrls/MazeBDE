package Maze;

public class MazeReadingException extends Exception {

    public MazeReadingException(String fileName, int lineOfError, String message) {
        super(message + " | File: " + fileName + " Line: " + lineOfError);

    }
}
