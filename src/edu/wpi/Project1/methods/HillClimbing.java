package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Graph;
import edu.wpi.Project1.util.NewNode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;

/**
 * Created by John on 9/11/2017.
 */
public class HillClimbing extends Algorithm {
    public HillClimbing(){
        this.printType = 2;
        this.name = "Hill-climbing (use the version of hill-climbing without backtracking)";
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
        frontier.addFirst(children.get(0));
        return frontier;
    }
}
