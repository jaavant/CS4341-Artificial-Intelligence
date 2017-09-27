package edu.wpi.Project2;

/**
 * Created by John on 9/26/2017.
 */
public class Game {
    private static Game instance = null;
    private Node state;

    protected Game(){
        state = new Node();
    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public String getNextMove(){
        return "YOUR NEXT MOVE";
    }

    public void applyOpponentsMove(String move){
        String[] words = move.split("\\b[^\\s]+\\b");
    }

}
