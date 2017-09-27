package edu.wpi.Project2;

/**
 * Created by John on 9/27/2017.
 */
public class SearchThread implements Runnable {
    private volatile boolean exit = false;

    @Override
    public void run() {
        Game game = Game.getInstance();
        int i = 1;
        while(!exit){
            game.setResult(game.searchState(i));
            i++;
        }
    }

    public void stop(){
        exit = true;
    }
}
