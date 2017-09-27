package edu.wpi.Project2;

/**
 * Created by John on 9/26/2017.
 */
public class Board {
    int squares[][];
    int column = 15;
    int rows = 15;
    
    public Board() {
    	squares = new int[15][15];
    	for (int i = 0; i < column; i++) {
    		for (int j = 0; j < rows; j++) {
    			squares[i][j] = 0;
    		}
    	}
    }

    /**
     *
     * @param ltr  X coordinate
     * @param num  Y coordinate
     * @param color  1 For our Team, 2 For their team
     */
    public void addStone(char ltr, int num, int color){
        int x = ltr  - 'a' + 1;
        int y = num;
        squares[y][x] = color;
    }
    
}
