package edu.wpi.Project2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by John on 9/26/2017.
 */
public class Node {
    private Board board;

   //Board representation [Starting from 1]
    private int moveX;
    private int moveY;

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

    //alpha beta pruning implementation
    private int search(Node node, int depth, int alpha, int beta, boolean maxPlayer){
        if(depth == 0 || node.isTerminal()){
            return node.getHeuristic();
        }
        if(maxPlayer){
            int v = Integer.MIN_VALUE;
            for(Node child : node.getChildren()){
                v = max(v, search(child,depth -1, alpha, beta, false));
                alpha = max(alpha,v);
                if(beta <= alpha){
                    break;
                }
            }
            return v;

        }
        else{
            int v = Integer.MAX_VALUE;
            for(Node child : node.getChildren()){
                v = min(v, search(child,depth -1, alpha, beta, true));
                beta = min(beta, v);
                if(beta <= alpha){
                    break;
                }
            }
            return v;
        }
    }
    //TODO
    public int getHeuristic(){
        return -1;
    }

    public void moveBoard(char ltr, int num, int color){
        board.addStone(ltr,num,color);
    }

    private boolean isTerminal(){
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
            if(x+w >= 15){
                horzR = false;
                break;
            }
            if(board.squares[y][x + w] != 1){
                horzR = false;
                break;
            }
        }
        if(horzR) return true;

        //Check horizontal right
        boolean horzL = true;
        for(int w = 1; w < 5; w++){
            if(x - w >= 0){
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
            if(y + h >= 15){
                vertU = false;
                break;
            }
            if(board.squares[y+h][x] != 1){
                vertU = false;
                break;
            }
        }
        if(vertU) return true;

        //Check vertical down
        boolean vertD = true;
        for(int h = 1; h < 5; h++){
            if(y - h <= 0){
                vertD = false;
                break;
            }
            if(board.squares[y-h][x] != 1){
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
        if(diagUL) return true;

        return false;
    }

}
