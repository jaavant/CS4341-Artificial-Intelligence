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
    
}
