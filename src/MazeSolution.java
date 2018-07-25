import java.io.*;
import java.util.*;

public class MazeSolution {

    private int[][] maze;
    private String currPath;
    private int currX;
    private int currY;
    private boolean unsolvable;
    //private int cheeseCount = 0;

    private int moves = 0;

    /*
            0 = open
            1 = wall
            2 = cheese
            3 = jerry
     */

    public void setJerry(int x, int y) {
        maze[x][y] = 3;
    }

    public static void main(String[] args) {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {

            Sing sing = Sing.instance();
            System.out.println("value: " + sing.value);

            // read params
            // num rows
            int x = new Integer(br.readLine()).intValue();

            // num cols
            int y = new Integer(br.readLine()).intValue();
            int[][] maze = new int[x][y];

            // read in row
            for(int i=0; i<y; i++) {
                String row = br.readLine();
                String[] vals = row.split(" ");
                int[] rowVals = new int[y];
                for (int j=0; j<vals.length; j++) {
                    rowVals[j] = new Integer(vals[j]);
                }
                // add to maze
                maze[i] = rowVals;
            }
            // jerry x
            int jerryx = new Integer(br.readLine()).intValue();
            // jerry y
            int jerryy = new Integer(br.readLine()).intValue();


            String startPath = "";
            int startX = 0;
            int startY = 0;

            MazeSolution solver = new MazeSolution(maze, startX, startY, startPath, false);

            // set Jerry's loc and solve
            solver.setJerry(jerryx, jerryy);
            solver.solveMaze();
            if (solver.unsolvable) {
                System.out.println("-1");
            } else {
                //System.out.println("Solved, A correct path is: " + solver.currPath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // testing
        int [][] customMaze =  {{0, 2, 0}, {1, 1, 2}, {1, 0, 0}};

        //{{0, 1, 0}, {1, 0, 1}, {0, 2, 2}};

        //{{0, 2, 0}, {0, 3, 1}, {1, 1, 1}};

        //solver.printMaze();

    }

    // constructor
    MazeSolution(int[][] aMaze, int stX, int stY, String currentPath, boolean noSolution) {
        maze = aMaze;
        currX = stX;
        currY = stY;
        currPath = currentPath;
        unsolvable = noSolution;
    }

    // indicate taken path
    void placePlus() {
        checkForCheese(currX, currY);
    }

    // for backtracking
    void placeMinus() {
    }

    private void checkDirAndMove(String cheeseDir) {

        if (cheeseDir.equals("E")) {
            currY++;
            placePlus();
            currPath += "E"; // Append East to our current path
        }
        if (cheeseDir.equals("W")) {
            currY--;
            placePlus();
            currPath += "W"; // Append East to our current path
        }
        if (cheeseDir.equals("N")) {
            currX--;
            placePlus();
            currPath += "N"; // Append East to our current path
        }
        if (cheeseDir.equals("S")) {
            currX++;
            placePlus();
            currPath += "S"; // Append East to our current path
        }
        // increment counter
        moves++;

        // check if we're looping
        if (moves > 100) {
            System.out.println("-1");
            System.exit(-1);
        }

        // recursive call continue searching
        solveMaze();
    }

    //
    // *******  solve
    //
    // priority in this order: look for Jerry, then Cheese, then Opening
    void solveMaze() {

        // check for Jerry
        if (checkForJerry(currX, currY)) {
            System.out.println(moves);
            //System.out.println("found Jerry in " + moves + " moves.");
            //System.out.println("cheese count: " + cheeseCount);
            System.exit(1);
        }

        // no Jerry, so look for cheese
        String cheeseDir = lookForCheese();
        if (cheeseDir.length() > 0) {
            checkDirAndMove(cheeseDir);
        }

        // look for Jerry
        String jerryDir = lookForJerry();
        if (jerryDir.length() > 0) {
            checkDirAndMove(jerryDir);
        }

        // No Jerry, so let's check for an opening
        String openDir = lookForOpen();
        if (openDir.length() > 0) {
            checkDirAndMove(openDir);
        } else { // we've hit a dead end, we need to backtrack
            if (currPath.length() == 0) {
                // we're back at the starting point, the maze is unsolvable
                unsolvable = true;
                return;
            } else {
                // we've reached a dead end, lets backtrack
                placeMinus();
                backTrack();
            }
        }
    }


    // see if the spot at a give x, y is open
    boolean checkForOpen(int x, int y) {
        return maze[x][y] == 0;
    }

    boolean checkForCheese(int x, int y) {
        boolean result = false;
        if (maze[x][y] == 2) {
            result = true;
            //cheeseCount++;
            // we got the cheese, so reset the position
            maze[x][y] = 0;
        }
        return result;
    }

    boolean checkForJerry(int x, int y) {
        int val = maze[x][y];
        return val == 3;
    }

    String lookForCheese() {
        String result = "";

        // make sure to protect against out of bounds as well
        if((currY + 1 < maze[currX].length && maze[currX][currY + 1] == 2) ||
                (currY - 1 >= 0  && maze[currX][currY - 1] == 2) ||
                (currX + 1 < maze[currX].length && maze[currX + 1][currY] == 2) ||
                (currX -1 >= 0 && maze[currX -1][currY] == 2)) {
            if (currY + 1 < maze[currX].length && maze[currX][currY + 1] == 2)
                result = "E";
            else if (currY - 1 >= 0  && maze[currX][currY - 1] == 2)
                result = "W";
            else if (currX + 1 < maze[currX].length && maze[currX + 1][currY] == 2)
                result = "S";
            else if (currX - 1 >= 0 && maze[currX -1][currY] == 2)
                result = "N";
        }
        return result;
    }

    String lookForJerry() {
        String result = "";

        // make sure to protect against out of bounds as well
        if((currY + 1 < maze[currX].length && maze[currX][currY + 1] == 3) ||
                (currY - 1 >= 0  && maze[currX][currY - 1] == 3) ||
                (currX + 1 < maze[currX].length && maze[currX + 1][currY] == 3) ||
                (currX -1 >= 0 && maze[currX -1][currY] == 3)) {
            if (currY + 1 < maze[currX].length && maze[currX][currY + 1] == 3)
                result = "E";
            else if (currY - 1 >= 0  && maze[currX][currY - 1] == 3)
                result = "W";
            else if (currX + 1 < maze[currX].length && maze[currX + 1][currY] == 3)
                result = "S";
            else if (currX - 1 >= 0 && maze[currX -1][currY] == 3)
                result = "N";
        }
        return result;
    }

    String lookForOpen() {
        String result = "";

        // make sure to protect against out of bounds as well
        if((currY + 1 < maze[currX].length && maze[currX][currY + 1] == 0) ||
                (currY - 1 >= 0  && maze[currX][currY - 1] == 0) ||
                (currX + 1 < maze[currX].length && maze[currX + 1][currY] == 0) ||
                (currX -1 >= 0 && maze[currX -1][currY] == 0)) {
            if (currY + 1 < maze[currX].length && maze[currX][currY + 1] == 0)
                result = "E";
            else if (currY - 1 >= 0  && maze[currX][currY - 1] == 0)
                result = "W";
            else if (currX + 1 < maze[currX].length && maze[currX + 1][currY] == 0)
                result = "S";
            else if (currX - 1 >= 0 && maze[currX -1][currY] == 0)
                result = "N";
        }
        return result;
    }

    void backTrack() {
        // sanity chek currPath.length() should always be > 0 when we call backTrack
        if (currPath.length() > 0) {
            placeMinus();
            switch (currPath.charAt(currPath.length() - 1)) {
                case 'E':
                    currY--;
                    break;
                case 'W':
                    currY++;
                    break;
                case 'S':
                    currX--;
                    break;
                case 'N':
                    currX++;
                    break;
            }
            currPath = currPath.substring(0, currPath.length()-1);
            solveMaze();
        }
    }

    void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            System.out.println(Arrays.toString(maze[i]));
        }
    }

}
