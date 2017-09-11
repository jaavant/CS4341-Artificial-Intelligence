package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Graph;
import edu.wpi.Project1.util.NewNode;

import java.util.ArrayDeque;
import java.util.List;

/**
 * Created by John on 9/11/2017.
 */
public class DepthFirstSearch extends Algorithm{
    public DepthFirstSearch(){
        this.printType = 0;
        this.name = "Depth 1st search";
    }
    @Override
    public ArrayDeque<NewNode> add(ArrayDeque<NewNode> frontier, List<NewNode> children, Graph graph) {
        for(int i = children.size() - 1; i >= 0; i--){
            frontier.addFirst(children.get(i));
        }
        return frontier;
    }
}
