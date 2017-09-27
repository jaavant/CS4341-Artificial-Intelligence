package edu.wpi.Project2;

import java.util.List;

/**
 * Created by John on 9/26/2017.
 */
public class Node {
    private Board board;

    public Node(){
        this.board = new Board();
    }

    //TODO
    public List<Node> getChildren(){
        return null;
    }

    //TODO
    public int getHeuristic(){
        return -1;
    }

    public void moveBoard(char ltr, int num, int color){
        board.addStone(ltr,num,color);
    }
}
