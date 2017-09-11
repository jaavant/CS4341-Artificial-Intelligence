package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Graph;
import edu.wpi.Project1.util.NewNode;

import java.util.ArrayDeque;
import java.util.List;

/**
 * Created by John on 9/5/2017.
 */
public class BreadthFirstSearch extends Algorithm {
   public BreadthFirstSearch(){
       this.printType = 0;
       this.name = "Breadth 1st search";
   }
    @Override
    public ArrayDeque<NewNode> add(ArrayDeque<NewNode> frontier, List<NewNode> children, Graph graph) {
        for(NewNode child: children){
            frontier.addLast(child);
        }
        return frontier;
    }
}
