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


    /**
     *  1 -> Up
     *  2-> Down
     *  3-> Right
     *  4-> Left
     *  5 -> Up Right
     *  6-> Up Left
     *  7 -> Down Right
     *  8 -> Down Left
     * @param color
     * @return
     */
    public HashMap<Integer,List<HashSet<Pair>>> countInRow(int color){
        HashMap<Integer,List<HashSet<Pair>>> map  = new HashMap<>();
        map.put(1,new ArrayList<HashSet<Pair>>());
        map.put(2,new ArrayList<HashSet<Pair>>());
        map.put(3,new ArrayList<HashSet<Pair>>());
        map.put(4,new ArrayList<HashSet<Pair>>());
        map.put(5,new ArrayList<HashSet<Pair>>());
        map.put(6,new ArrayList<HashSet<Pair>>());
        map.put(7,new ArrayList<HashSet<Pair>>());
        map.put(8,new ArrayList<HashSet<Pair>>());


        for(int i = 0; i < 15; i++){
            for(int k = 0; k < 15; k++){
                if(board.squares[i][k] != color){
                    continue;
                }
                Pair pair = new Pair(i,k);
                boolean blockedLeft;
                boolean blockedRight;
                boolean blockedUp;
                boolean blockedDown;

                //Left
                if(!inMap(map, 4, pair)){
                    HashSet<Pair> set = new HashSet<>();
                    set.add(pair);
                    int blockers = 0;
                    if(blocked(3,pair,color)) blockers++;

                    for(int m = 1; m < 4; m++){
                        if(pair.x - m > 0 && (board.squares[i][k-m] != color && board.squares[i][k-m] != 0)){
                            blockers++;
                            break;
                        }
                        else{
                            set.add(new Pair(pair.y, pair.x - m));
                        }
                    }

                    if(blockers < 2 && set.size() >= 2){
                        map.get(4).add(set);
                    }
                }
                //Down
                if(!inMap(map, 2, pair)){
                    HashSet<Pair> set = new HashSet<>();
                    set.add(pair);
                    int blockers = 0;
                    if(blocked(1,pair,color)) blockers++;

                    for(int m = 1; m < 4; m++){
                        if(pair.y + m < 14 && (board.squares[i+m][k] != color && board.squares[i+m][k] != 0)){
                            blockers++;
                            break;
                        }
                        else{
                            set.add(new Pair(pair.y+m, pair.x));
                        }
                    }

                    if(blockers < 2 && set.size() >= 2){
                        map.get(2).add(set);
                    }
                }
                //Right
                if(!inMap(map, 3, pair)){
                    HashSet<Pair> set = new HashSet<>();
                    set.add(pair);
                    int blockers = 0;
                    if(blocked(4,pair,color)) blockers++;

                    for(int m = 1; m < 4; m++){
                        if(pair.x + m < 14 && (board.squares[i][k+m] != color && board.squares[i][k+m] != 0)){
                            blockers++;
                            break;
                        }
                        else{
                            set.add(new Pair(pair.y, pair.x+m));
                        }
                    }

                    if(blockers < 2 && set.size() >= 2){
                        map.get(3).add(set);
                    }
                }
                //Up
                if(!inMap(map, 1, pair)){
                    HashSet<Pair> set = new HashSet<>();
                    set.add(pair);
                    int blockers = 0;
                    if(blocked(2,pair,color)) blockers++;

                    for(int m = 1; m < 4; m++){
                        if(pair.y - m > 0 && (board.squares[i-m][k] != color && board.squares[i-m][k] != 0)){
                            blockers++;
                            break;
                        }
                        else{
                            set.add(new Pair(pair.y - m, pair.x));
                        }
                    }

                    if(blockers < 2 && set.size() >= 2){
                        map.get(1).add(set);
                    }
                }
                //UP Right
                if(!inMap(map, 5, pair)){
                    HashSet<Pair> set = new HashSet<>();
                    set.add(pair);
                    int blockers = 0;
                    if(blocked(1,pair,color) || blocked(3,pair,color)) blockers++;

                    for(int m = 1; m < 4; m++){
                        if(pair.y - m > 0 && pair.x + m < 14 && (board.squares[i-m][k+m] != color && board.squares[i-m][k+m] != 0)){
                            blockers++;
                            break;
                        }
                        else{
                            set.add(new Pair(pair.y-m, pair.x+m));
                        }
                    }

                    if(blockers < 2 && set.size() >= 2){
                        map.get(5).add(set);
                    }
                }
                //Up Left
                if(!inMap(map, 6, pair)){
                    HashSet<Pair> set = new HashSet<>();
                    set.add(pair);
                    int blockers = 0;
                    if(blocked(4,pair,color) || blocked(1,pair,color)) blockers++;

                    for(int m = 1; m < 4; m++){
                        if(pair.y - m > 0 && pair.x - m > 0 && (board.squares[i-m][k-m] != color && board.squares[i-m][k-m] != 0)){
                            blockers++;
                            break;
                        }
                        else{
                            set.add(new Pair(pair.y-m, pair.x-m));
                        }
                    }

                    if(blockers < 2 && set.size() >= 2){
                        map.get(6).add(set);
                    }
                }
                //Down Right
                if(!inMap(map, 7, pair)){
                    HashSet<Pair> set = new HashSet<>();
                    set.add(pair);
                    int blockers = 0;
                    if(blocked(2,pair,color) || blocked(3,pair,color)) blockers++;

                    for(int m = 1; m < 4; m++){
                        if(pair.y + m < 14 && pair.x + m < 14 && (board.squares[i+m][k+m] != color && board.squares[i+m][k+m] != 0)){
                            blockers++;
                            break;
                        }
                        else{
                            set.add(new Pair(pair.y+m, pair.x+m));
                        }
                    }

                    if(blockers < 2 && set.size() >= 2){
                        map.get(7).add(set);
                    }
                }
                //Down Left
                if(!inMap(map, 8, pair)){
                    HashSet<Pair> set = new HashSet<>();
                    set.add(pair);
                    int blockers = 0;
                    if(blocked(4,pair,color) || blocked(2,pair,color)) blockers++;

                    for(int m = 1; m < 4; m++){
                        if(pair.y + m < 14 && pair.x - m > 0 && (board.squares[i+m][k-m] != color && board.squares[i+m][k-m] != 0)){
                            blockers++;
                            break;
                        }
                        else{
                            set.add(new Pair(pair.y+m, pair.x-m));
                        }
                    }

                    if(blockers < 2 && set.size() >= 2){
                        map.get(8).add(set);
                    }
                }

            }
        }
        return map;
    }

    /**
     * 1 -> up
     * 2 -> down
     * 3 -> right
     * 4 -> left
     * @param dir
     * @return
     */
    private boolean blocked(int dir, Pair pair, int color){
        if(dir == 1){
            if(pair.y - 1 < 0 || (board.squares[pair.y -1 ][pair.x] != color && board.squares[pair.y - 1][pair.x] != 0)){
                return true;
            }
        }
        else if(dir == 2){
            if(pair.y + 1 > 14 || (board.squares[pair.y + 1][pair.x] != color && board.squares[pair.y + 1][pair.x] != 0)){
                return true;
            }
        }
        else if(dir == 3){
            if(pair.x + 1 > 14 || (board.squares[pair.y][pair.x + 1] != color && board.squares[pair.y][pair.x + 1] != 0)){
                return true;
            }
        }
        else if(dir == 4){
            if(pair.x - 1< 0 || (board.squares[pair.y][pair.x - 1] != color && board.squares[pair.y][pair.x - 1] != 0)){
                return true;
            }
        }
        return false;
    }

    private boolean inMap(HashMap<Integer,List<HashSet<Pair>>> map, int direction, Pair pair){
        for(HashSet set : map.get(direction)){
            if(set.contains(pair)){
                return true;
            }
        }
        return false;
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
