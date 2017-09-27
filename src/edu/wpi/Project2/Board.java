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

    public Board(int[][] matrix){
        this.squares = matrix;
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
        squares[y-1][x-1] = color;
    }


    @Override
    public Board clone(){
        int[][] newMatrix = new int[15][15];
        for(int i = 0; i < 15; i++){
            for(int k = 0; k < 15; k++){
                newMatrix[i][k] = this.squares[i][k];
            }
        }

        return new Board(newMatrix);
    }
}
