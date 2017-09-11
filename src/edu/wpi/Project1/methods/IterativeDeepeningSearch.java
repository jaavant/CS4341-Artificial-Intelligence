package edu.wpi.Project1.methods;

import edu.wpi.Project1.util.Graph;
import edu.wpi.Project1.util.NewNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by John on 9/11/2017.
 */
public class IterativeDeepeningSearch extends Algorithm {
    public int iteration;

    public IterativeDeepeningSearch(){
        this.printType = 0;
        this.name = "Iterative deepening search ";
        this.iteration = 0;
    }
    @Override
    public ArrayDeque<NewNode> add(ArrayDeque<NewNode> frontier, List<NewNode> children, Graph graph) {
        NewNode child;
        for(int i = children.size() - 1; i >= 0; i--){
            child = children.get(i);
            if(child.getDepth() > this.iteration){
                continue;
            }
            frontier.addFirst(child);
        }

        if(frontier.isEmpty() && iteration < 26) {
            LinkedList path = new LinkedList();
            path.add('S');
            frontier.add(new NewNode(0,graph.getHeur('S'),0,0,'S',path));
            this.iteration++;
            System.out.print("\n L = " + this.iteration + "\n");
        }

        return frontier;
    }
}
