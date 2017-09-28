package edu.wpi.Project2;

import java.util.concurrent.TimeUnit;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by John on 9/26/2017.
 */
public class Game {
    private static Game instance = null;
    private Node state;
    private volatile int result[];

    protected Game(){
        state = new Node();
        state.moveX = 8;
        state.moveY = 7;
    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public synchronized int[] getResult(){
        return this.result;
    }

    public synchronized void setResult(int[] result){
        this.result = result;
    }

    public String getNextMove(){
        SearchThread runner = new SearchThread();
        Thread thread = new Thread(runner);
        thread.start();

        try {
            TimeUnit.MILLISECONDS.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runner.stop();

        char ltr  = (char) (getResult()[1] + 'a' - 1);
        int y = getResult()[2];
        state.moveBoard(ltr,y,1);

        return "Cortana " + ltr + " " + y;
    }

    public int[] searchState(int depth){
        return result = alphabeta(state, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
    }

    //alpha beta pruning implementation
     private int[] alphabeta(Node node, int depth, int alpha, int beta, boolean maxPlayer){
        if(depth == 0 || node.isTerminal()){
            int[] result = {node.getHeuristic(),node.moveX,node.moveY};
            return result;
        }
        if(maxPlayer){
            int v[] =  {Integer.MIN_VALUE};
            for(Node child : node.getChildren()){
                int[] srch = alphabeta(child,depth -1, alpha, beta, false);
                v = (v[0] > srch[0])?  v : srch;
                alpha = max(alpha,v[0]);
                if(beta <= alpha){
                    break;
                }
            }
            return v;
        }
        else{
            int[] v = {Integer.MAX_VALUE};
            for(Node child : node.getChildren()){
                int[] srch = alphabeta(child,depth -1, alpha, beta, true);
                v = (v[0] < srch[0]) ? v : srch;
                beta = min(beta, v[0]);
                if(beta <= alpha){
                    break;
                }
            }
            return v;
        }
    }

    public void applyOpponentsMove(String move){
        String[] words = move.toLowerCase().split("\\b[^\\s]+\\b");
        state.moveBoard(words[1].charAt(0), Integer.parseInt(words[2]), 2);
    }

}
