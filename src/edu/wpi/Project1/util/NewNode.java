package edu.wpi.Project1.util;

import com.sun.org.apache.bcel.internal.generic.NEW;
import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * Created by John on 9/10/2017.
 */
public class NewNode {
    private Double g;
    private Double h;
    private Double f;
    private Double weight;
    private int depth;
    private char letter;
    LinkedList<Character> path;

    public NewNode(double g, double h, double weight, int depth, char letter, LinkedList<Character> path){
        this.g = g;
        this.h = h;
        this.f = g + h;
        this.weight = weight;
        this.depth = depth;
        this.letter = letter;
        this.path = path;
    }

    public ArrayList<NewNode> getChildren(Graph graph){
        ArrayList<NewNode> children = new ArrayList<>();

        for(int k = 0; k < 26; k++){
            Double w;
            LinkedList<Character> p;
            Double h;
            Character ltr =  Character.valueOf((char)(k + 65));

            if(path.size() > 1 && ltr.equals(this.path.get(1))){
                continue;
            }

            if((h = graph.getHeur(ltr)) == null){
                continue;
            }
            if((w = graph.getWeight(this.letter,ltr)) == null){
                continue;
            }
            p = (LinkedList<Character>) this.path.clone();

            if(p.contains(ltr)){
                continue;
            }
            p.addFirst(ltr);

            double localG = this.g.doubleValue();
            double localW = w.doubleValue();

            children.add(new NewNode(localG + localW,h.doubleValue(),w,this.depth + 1,ltr, p));

        }

        return children;
    }

    public String toString(int printType){
        StringBuilder string =new StringBuilder("");
        String heuristic;
        Iterator<Character> iter = this.path.iterator();

        if(printType == 1){
            string.append(this.g);
        }
        else if(printType == 2){
            string.append(this.h);
        }
        string.append("<");
        while(iter.hasNext()){
            string.append(iter.next().toString());

            if(iter.hasNext()){
                string.append(",");
            }
        }

        string.append(">");
        return string.toString();
    }

    public Character getLetter(){
        return this.letter;
    }

    public LinkedList<Character> getPath(){
        return this.path;
    }

    public int getDepth(){ return this.depth; }

    public Double getWeight(){
        return this.weight;
    }

    public Double getG() {
        return g;
    }

    public Double getH() {
        return h;
    }

    public Double getF() {
        return f;
    }
}
