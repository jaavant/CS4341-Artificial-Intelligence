package edu.wpi.Project1.old;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Created by John on 9/5/2017.
 */
public class Tuple {
    private ArrayDeque<Node> path;
    private Double heur;

    public Tuple(Double heur, Node node){
        this.path = new ArrayDeque<>();
        this.heur = heur;
        this.path.addFirst(node);
    }

    public ArrayDeque<Node> getPath() {
        return path;
    }

    public void addNode(Node node){
        this.path.addFirst(node);
    }
    public Node getNode(){
        return path.getLast();
    }

    public double getHeur() {
        return heur;
    }

    public void setHeur(double weight) {
        this.heur = weight;
    }

    @Override
    public String toString(){
        StringBuilder string;
        String heuristic;
        Iterator<Node> iter = this.path.iterator();

        if(heur == null){
            heuristic = "";
        }
        else{
            heuristic = heur.toString();
        }
        string = new StringBuilder(heuristic);
        string.append("<");

        while(iter.hasNext()){
            string.append(iter.next().toString());

            if(iter.hasNext()){
                string.append(",");
            }
        }

        return string.toString();
    }
}
