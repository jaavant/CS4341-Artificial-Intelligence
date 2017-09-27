package edu.wpi.Project2;

import static java.util.Objects.hash;

/**
 * Created by John on 9/27/2017.
 */
public class Pair {
    int x,y;

    public Pair(int y,int x){
        this.y = y;
        this.x = x;
    }

    @Override
    public int hashCode(){
       String word = y + "" + x;
       return hash(word);
    }

    @Override
    public boolean equals(Object obj){
        Pair other = (Pair) obj;
        if(other.x == this.x && other.y == this.y){
            return true;
        }
        else{
            return false;
        }
    }
}
