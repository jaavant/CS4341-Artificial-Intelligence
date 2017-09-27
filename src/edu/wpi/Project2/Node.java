package edu.wpi.Project2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by John on 9/26/2017.
 */
public class Node {
    private Board board;

    public Node(){
        this.board = new Board();
    }

    public Node(Board board){
        this.board = board;
    }

    //TODO
    public List<Node> getChildren(){
       ArrayList<Node> children = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            for(int k = 0; k < 15; k++){
                if(board.squares[i][k] == 0){
                    new Node(board.clone());
                }
            }
        }
        Collections.shuffle(children);
        return children;
    }

    //TODO
    public int getHeuristic(){
        return -1;
    }

    public void moveBoard(char ltr, int num, int color){
        board.addStone(ltr,num,color);
    }
}
