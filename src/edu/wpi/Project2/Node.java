package edu.wpi.Project2;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by John on 9/26/2017.
 */
public class Node {
    private Board board;

   //Board representation [Starting from 1]
    int moveX;
    int moveY;

    public Node(){
        this.board = new Board();
        this.moveX = -1;
        this.moveY = -1;
    }

    public Node(int moveX, int moveY){
        this.board = new Board();
        this.moveX = moveX;
        this.moveY = moveY;
    }

    public Node(Board board){
        this.board = board;
    }

    public Node(Board boardInt, int moveX, int moveY){
        this.board = boardInt;
        this.moveX = moveX;
        this.moveY = moveY;
    }

    //TODO
    public List<Node> getChildren(){
       ArrayList<Node> children = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            for(int k = 0; k < 15; k++){
                if(board.squares[i][k] == 0){
                    new Node(board.clone(),k+1, i+1);
                }
            }
        }
        Collections.shuffle(children);
        return children;
    }

    //TODO
    public int getHeuristic(){
        return calcHeurVal(1) - calcHeurVal(2);
    }

    public int calcHeurVal(int i){
        int player1 = 0;
        HashMap<Integer,List<HashSet<Pair>>> map = countInRow(i);
        for(Map.Entry<Integer,List<HashSet<Pair>>> entry :  map.entrySet()){
            for(HashSet set : entry.getValue()){
            	//int runLen = set.size();
            	//player1 += 10 ^ (runLen - 2);
            	
                if(set.size() == 2){
                    player1 += 1;
                }
                else if(set.size() == 3){
                    player1 += 10;
                }
                else if(set.size() == 4){
                    player1 += 100;
                }
                else if(set.size() == 5){
                    player1 += 1000;
                }
            }
        }
        return player1;
    }



    public HashMap<Integer,List<HashSet<Pair>>> countInRow(int i){
        return null;
    }

    public void moveBoard(char ltr, int num, int color){
        board.addStone(ltr,num,color);
    }

    public boolean isTerminal(){
        boolean full = true;
        outerLoop:
        for(int i = 0; i < 15; i++){
            for(int k = 0; k < 15; k++){
                if(board.squares[i][k] == 0){
                    full = false;
                    break outerLoop;
                }
            }
        }
        return full || fiveInRow();
    }

    private boolean fiveInRow(){
        int x = moveX - 1; int y = moveY -1;

        //Check horizontal right
        boolean horzR = true;
        for(int w = 1; w < 5; w++){
            if(x + w >= 15){
                horzR = false;
                break;
            }
            if(board.squares[y][x + w] != 1){
                horzR = false;
                break;
            }
        }
        if(horzR) return true;

        //Check horizontal left
        boolean horzL = true;
        for(int w = 1; w < 5; w++){
            if(x - w <= 0){
                horzL = false;
                break;
            }
            if(board.squares[y][x - w] != 1){
                horzL = false;
                break;
            }
        }
        if(horzL) return true;


        //Check vertical up
        boolean vertU = true;
        for(int h = 1; h < 5; h++){
            if(y - h <= 0){
                vertU = false;
                break;
            }
            if(board.squares[y-h][x] != 1){
                vertU = false;
                break;
            }
        }
        if(vertU) return true;

        //Check vertical down
        boolean vertD = true;
        for(int h = 1; h < 5; h++){
            if(y + h >= 15){
                vertD = false;
                break;
            }
            if(board.squares[y+h][x] != 1){
                vertD = false;
                break;
            }
        }
        if(vertD) return true;


        //Check Diagonal Up Right
        boolean diagUR = true;
        for(int j = 1; j < 5; j++){
            if(y - j <= 0 || x + j >=15){
                diagUR = false;
                break;
            }
            if(board.squares[y-j][x+j] != 1){
                diagUR = false;
                break;
            }
        }
        if(diagUR) return true;

        //Check Diagonal Up Left
        boolean diagUL = true;
        for(int j = 1; j < 5; j++){
            if(y - j <= 0 || x - j <= 0){
                diagUL = false;
                break;
            }
            if(board.squares[y-j][x-j] != 1){
                diagUL = false;
                break;
            }
        }
        if(diagUL) return true;

        //Check Diagonal Down Right
        boolean diagDR = true;
        for(int j = 1; j < 5; j++){
            if(y + j >= 15 || x + j >=15){
                diagDR = false;
                break;
            }
            if(board.squares[y+j][x+j] != 1){
                diagDR = false;
                break;
            }
        }
        if(diagDR) return true;

        //Check Diagonal Down Left
        boolean diagDL = true;
        for(int j = 1; j < 5; j++){
            if(y + j >= 15 || x - j <= 0){
                diagDL = false;
                break;
            }
            if(board.squares[y+j][x-j] != 1){
                diagDL = false;
                break;
            }
        }
        if(diagDL) return true;

        return false;
    }

}
