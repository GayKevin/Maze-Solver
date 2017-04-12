/**
 * Created by Kevin Gay on 09/04/2017.
 */

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main Class
 */
public class MazeSolver {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Boolean isWorking = true;

        while(isWorking) {
            System.out.print("Select the level of the Maze.\n" +
                    "a Level 0\n" +
                    "b Level 1\n" +
                    "c Level 2\n" +
                    "d Level 3\n" +
                    "q: Exit the maze\n" +
                    "Select your maze: ");

            if (in.hasNextLine()) {
                switch (in.nextLine()) {
                    case "a":
                        chooseAlgo(parseFiles("Maze-Level0"));
                        break;
                    case "b":
                        chooseAlgo(parseFiles("Maze-Level1"));
                        break;
                    case "c":
                        chooseAlgo(parseFiles("Maze-Level2"));
                        break;
                    case "d":
                        chooseAlgo(parseFiles("Maze-Level3"));
                        break;
                    case "q":
                        isWorking = false;
                        break;
                }
            }
        }
    }

    /**
     * Choose which algo you want
     * @param maze array
     */
    private static void chooseAlgo(char[][] maze){
        boolean isWorking = true;
        Scanner in = new Scanner(System.in);
        Maze mazeArray = new Maze(maze);

        while (isWorking){
            System.out.print("Select the algo.\n" +
                    "a DFS\n" +
                    "b BFS\n" +
                    "Select your maze: ");
            if (in.hasNextLine()){
                switch (in.nextLine()){
                    case "a":
                        DFS(mazeArray);
                        isWorking = false;
                        break;
                    case "b":
                        BFS(mazeArray);
                        isWorking = false;
                        break;
                }
            }
        }
    }

    /**
     * DFS Algo
     * @param maze Class
     */
    private static void DFS(Maze maze){
        LinkedStack stack = new LinkedStack();

        if (findFirst(maze.getMaze()) != null){
            stack.push(findFirst(maze.getMaze()));
        }

        resolveDFS(maze, stack);
        displayMaze(maze.getMaze());
    }

    /**
     * BFS Algo
     * @param maze string
     */
    private static void BFS(Maze maze){
        LinkedQueue queue = new LinkedQueue();

        if (findFirst(maze.getMaze()) != null){
            queue.add(findFirst(maze.getMaze()));
        }

        resolveBFS(maze, queue);
        displayMaze(maze.getMaze());
    }

    /**
     * Check if you have find the end
     * @param maze string
     * @param stack path
     * @return stack
     */
    private static boolean checkEnd(Maze maze, LinkedStack stack){
        if (stack.peek() != null) {
            if (maze.getMaze()[(int)stack.peek().getX()][(int)stack.peek().getY()] == 'f') {
                return true;
            }
        }
        return false;
    }

    /**
     * Check Maze neighbour
     * @param maze string
     * @param stack who contains the path
     * @param x position
     * @param y position
     * @return True if it find the end
     */
    private static Boolean checkMaze(Maze maze, LinkedStack stack, int x, int y){
        if (checkEnd(maze, stack)) return true;

        if (x <= maze.getMaze().length && y <= maze.getMaze()[x].length) {
            if (maze.getMaze()[x][y] == ' ') {
                if (maze.getMaze()[x][y] != '.') {
                    maze.getMaze()[x][y] = '.';
                    stack.push(new Point(x, y));
                    resolveDFS(maze, stack);
                }
            } else if (maze.getMaze()[x][y] == 'f') {
                stack.push(new Point(x, y));
                return true;
            }
        }
        return false;
    }

    /**
     * Check neighbour
     * @param maze string
     * @param queue who contains the path
     * @param x position
     * @param y position
     * @return true or false
     */
    private static boolean checkMaze(Maze maze, LinkedQueue queue, int x, int y){
        if (x <= maze.getMaze().length && y <= maze.getMaze()[x].length) {
            if ( maze.getMaze()[x][y] == ' ') {
                if (maze.getMaze()[x][y] != '.') {
                    queue.add(new Point(x, y));
                    maze.getMaze()[x][y] = '.';
                }
            } else if (maze.getMaze()[x][y] == 'f') {
                queue.add(new Point(x, y));
                maze.setLastPoint(new Point(x, y));
                return true;
            }
        }
        return false;
    }

    /**
     * Recursive to find the path with DFS
     * @param maze string
     * @param stack path
     */
    private static void resolveDFS(Maze maze, LinkedStack stack){
        int x = (int)stack.peek().getX();
        int y = (int)stack.peek().getY();

        if (checkMaze(maze, stack, x, y + 1)) return;

        if (checkMaze(maze, stack, x - 1, y)) return;

        if (checkMaze(maze, stack, x + 1, y)) return;

        if(checkMaze(maze, stack, x, y - 1)) return;

        if (stack.peek() != null) {
            if ( maze.getMaze()[(int)stack.peek().getX()][(int)stack.peek().getY()] != 'f') {
                maze.getMaze()[x][y] = ' ';
                stack.pop();
            }
        }
    }

    /**
     * Recursive to find the path with BFS
     * @param maze string
     * @param queue path
     */
    private static void resolveBFS(Maze maze, LinkedQueue queue){
        int x = (int)queue.peek().getX();
        int y = (int)queue.peek().getY();

        queue.remove();

        checkMaze(maze, queue, x, y + 1);

        checkMaze(maze, queue, x - 1, y);

        checkMaze(maze, queue, x + 1, y);

        checkMaze(maze, queue, x, y - 1);

        if (findPath(maze, queue, x, y)) return;

        resolveBFS(maze, queue);

        findPath(maze, queue, x, y);
    }

    /**
     * Find the path for the BFS
     * @param maze class
     * @param queue who contains the point
     * @param x position
     * @param y posistion
     * @return true the peek is the end
     */
    private static Boolean findPath(Maze maze, LinkedQueue queue, int x, int y){
        if (maze.getMaze()[(int)queue.peek().getX()][(int)queue.peek().getY()] == 'f') {
            if (!checkNeighbour(maze, x, y))
                maze.getMaze()[x][y] = ' ';
            else
                maze.setLastPoint(new Point(x, y));
            return true;
        }
        return false;
    }

    /**
     * Check the neighbour after to find the end for BFS
     * @param maze class
     * @param x position
     * @param y position
     * @return true if the last point is next to this current point
     */
    private static Boolean checkNeighbour(Maze maze, int x, int y) {
        return maze.getLastPoint().getX() == x + 1 && maze.getLastPoint().getY() == y||
                maze.getLastPoint().getX() == x - 1 && maze.getLastPoint().getY() == y||
                maze.getLastPoint().getY() == y + 1 && maze.getLastPoint().getX() == x||
                maze.getLastPoint().getY() == y - 1 && maze.getLastPoint().getX() == x;
    }

    /**
     * Find the first point
     * @param maze string
     * @return the first point
     */
    private static Point findFirst(char[][] maze){
        for (int i = 0; i < maze.length; i++){
            String str = new String(maze[i]);
            if (str.contains("s")){
                return new Point(str.indexOf('s'), i);
            }
        }
        return null;
    }

    /**
     * Display the maze
     * @param maze string
     */
    private static void displayMaze(char[][] maze){
        for (int x = 0; x < maze.length; x++)
            System.out.println(maze[x]);
    }

    /**
     * Parse the file to get the maze
     * @param name of the file
     */
    private static char[][] parseFiles(String name){
        int i = 0;
        File file = new File(name + ".txt") ;

        try {
            Scanner sc = new Scanner(file);
            if (sc.hasNextLine()) {
                char[][] maze =  new char[sc.nextInt()][sc.nextInt()];
                sc.nextLine();
                while(sc.hasNextLine()){
                    maze[i] = sc.nextLine().toCharArray();
                    i++;
                }
                sc.close();
                return maze;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("We didnt find your file");
        }
        return null;
    }
}
