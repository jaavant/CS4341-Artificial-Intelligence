package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Graph;
import edu.wpi.Project1.util.NewNode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;

/**
 * Created by John on 9/11/2017.
 */
public class BeamSearch extends Algorithm {
    private int k;
    public BeamSearch(int k){
        this.k = k;
        this.printType = 2;
        this.name = "Beam search (use w = 2)";
    }

    @Override
    public ArrayDeque<NewNode> add(ArrayDeque<NewNode> frontier, List<NewNode> children, Graph graph) {
        children.sort(new Comparator<NewNode>() {
            @Override
            public int compare(NewNode o1, NewNode o2) {
                if (o1.getH() < o2.getH()){
                    return -1;
                }
                else if(o1.getH() > o2.getH()){
                    return 1;
                }
                else{
                    if(o2.getLetter() > o1.getLetter()){
                        return -1;
                    }
                    else if (o1.getLetter() > o2.getLetter()){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
            }
        });
        children.subList(0,this.k);
        for(int i = children.size(); i >= 0; i--){
            frontier.addFirst(children.get(i));
        }
        return frontier;
    }
}
