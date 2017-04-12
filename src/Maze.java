import java.awt.*;

/**
 * Created by Kevin Gay on 12/04/2017.
 */

/**
 * Class Maze
 */
public class Maze {
    /**
     * Maze map
     */
    private char[][] maze = null;
    /**
     * Last Point for the BFS
     */
    private Point lastPoint = null;

    /**
     * Constructor
      * @param maze Map
     */
    Maze(char[][] maze){
        this.maze = maze;
    }

    /**
     * Get Maze map
     * @return maze
     */
     char[][] getMaze() {
        return maze;
    }

    /**
     * Get Last Point
     * @return last Point
     */
    Point getLastPoint() {
        return lastPoint;
    }

    /**
     * Set the last point
     * @param lastPoint of the map
     */
    void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
    }
}
