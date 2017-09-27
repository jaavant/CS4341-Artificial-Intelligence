package edu.wpi.Project2;

/**
 * Created by John on 9/26/2017.
 */
public class Game {
	
	private static Game instance = null;
	private Board board;
    protected Game(){
    	board = new Board();
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
        System.out.println("APPLYING MOVE: " + move);
    }
    
}
